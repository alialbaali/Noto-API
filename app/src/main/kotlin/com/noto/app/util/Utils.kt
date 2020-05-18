package com.noto.app.util

import com.noto.domain.model.User
import com.noto.domain.schema.ResponseSchema
import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.application.ApplicationCall
import io.ktor.http.HttpStatusCode
import io.ktor.util.KtorExperimentalAPI
import io.ktor.util.hex
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

suspend inline fun ApplicationCall.respond(status: HttpStatusCode, success: Boolean, error: String? = null, data: Any? = null) {
    val message = ResponseSchema(success, error, data)
    response.status(status)
    response.pipeline.execute(this, message)
}

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