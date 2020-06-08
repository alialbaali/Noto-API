package com.noto.app.util

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.noto.domain.model.User
import com.noto.domain.schema.ResponseSchema
import com.noto.domain.schema.UserResponse
import io.ktor.application.ApplicationCall
import io.ktor.http.HttpStatusCode
import io.ktor.util.KtorExperimentalAPI
import io.ktor.util.hex
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import javax.mail.Message
import javax.mail.Session
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage


suspend inline fun ApplicationCall.respond(
    status: HttpStatusCode,
    success: Boolean,
    error: String? = null,
    data: Any? = null
) {
    val message = ResponseSchema(success, error, data)
    response.status(status)
    response.pipeline.execute(this, message)
}


//suspend inline fun <T> ApplicationCall.respond(
//    status: HttpStatusCode,
//    success: Boolean,
//    error: String? = null,
//    data: T? = null
//) {
//    val message = ResponseSchema<T>(success, error, data)
//    response.status(status)
//    response.pipeline.execute(this, message)
//}


@KtorExperimentalAPI
fun String.hash(): String {
    val hashKey = hex(System.getenv("HASH_SECRET"))
    val hmacKey = SecretKeySpec(hashKey, "HmacSHA1")
    val hmac = Mac.getInstance("HmacSHA1")
    hmac.init(hmacKey)
    return hex(hmac.doFinal(this.toByteArray(Charsets.UTF_8)))
}

private val algorithm = Algorithm.HMAC256(System.getenv("JWT_SECRET"))
private val jwtIssuer = System.getenv("JWT_ISSUER")
private val jwtAudience = System.getenv("JWT_AUDIENCE")

val jwtVerifier: JWTVerifier = JWT.require(algorithm)
    .withIssuer(jwtIssuer)
    .withAudience(jwtAudience)
    .build()

fun User.generateToken(): String {
    return JWT.create()
        .withIssuer(jwtIssuer)
        .withAudience(jwtAudience)
        .withClaim("userId", userId)
        .sign(algorithm)
}

fun User.getResponse(): UserResponse {
    return UserResponse(
        userDisplayName,
        userEmail,
        generateToken()
    )
}

fun User.sendEmail() {
    try {

        val username = System.getenv("EMAIL")
        val password = System.getenv("PASSWORD")
        val recipient = this.userEmail


        val host = "smtp.gmail.com"
        val properties = System.getProperties().also { props ->
            props["mail.smtp.starttls.enable"] = "true"
            props["mail.smtp.host"] = host
            props["mail.smtp.user"] = username
            props["mail.smtp.password"] = password
            props["mail.smtp.port"] = "587"
            props["mail.smtp.auth"] = "true"
        }

        val session = Session.getInstance(properties)

        val message = MimeMessage(session)


        message.setFrom("noreply@noto.com")
        message.setRecipient(Message.RecipientType.TO, InternetAddress(recipient))

        message.subject = "Verification"
        message.setText("HELLO")
        val transport = session.getTransport("smtp")
        transport.connect(host, username, password)
        transport.sendMessage(message, message.allRecipients)
        transport.close()

    } catch (e: Throwable) {
        println(e.toString())
    }

}