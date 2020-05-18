package com.noto.app

import com.noto.app.controller.label
import com.noto.app.controller.library
import com.noto.app.controller.noto
import com.noto.app.controller.user
import com.noto.app.util.jwtVerifier
import com.noto.app.util.respond
import com.noto.di.*
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.auth.authentication
import io.ktor.auth.jwt.JWTPrincipal
import io.ktor.auth.jwt.jwt
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.features.StatusPages
import io.ktor.http.HttpStatusCode
import io.ktor.jackson.jackson
import io.ktor.request.path
import io.ktor.routing.routing
import io.ktor.util.KtorExperimentalAPI
import org.koin.ktor.ext.Koin
import org.slf4j.event.Level

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@KtorExperimentalAPI
@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.main(testing: Boolean = false) {
    install(CallLogging) {
        level = Level.INFO
        filter { call -> call.request.path().startsWith("/") }
    }
    initDB()


    install(Koin) {
        modules(
            repositoryModule,
            dataSourceModule,
            userUseCasesModule,
            libraryUseCasesModule,
            notoUseCasesModule,
            labelUseCasesModule
        )
    }

    install(DefaultHeaders) {
        header("X-Engine", "Ktor") // will send this header with each schema
    }

    // https://ktor.io/servers/features/https-redirect.html#testing
//    if (!testing) {
//        install(HttpsRedirect) {
//            // The port to redirect to. By default 443, the default HTTPS port.
//            sslPort = 443
//            // 301 Moved Permanently, or 302 Found redirect.
//            permanentRedirect = true
//        }
//    }

    install(ContentNegotiation) {
        jackson()
    }

    install(StatusPages) {
        exception<AuthenticationException> { cause ->
            call.respond(HttpStatusCode.Unauthorized, false, cause.message)
        }
        exception<AuthorizationException> { cause ->
            call.respond(HttpStatusCode.Forbidden, false, cause.message)
        }
        exception<BadRequestException> { cause ->
            call.respond(HttpStatusCode.BadRequest, false, cause.message)
        }
        exception<BadRequestException> { cause ->
            call.respond(HttpStatusCode.NotFound, false, cause.message)
        }

    }

    authentication {
        jwt {
            realm = System.getenv("REALM")
            verifier(jwtVerifier)

            challenge { _, _ ->
                throw AuthorizationException("Invalid token")
            }

            validate { token ->
                // Verify user by checking the id from payload thru database
                JWTPrincipal(token.payload)
            }
        }
    }


    routing {
        user()
        library()
        noto()
        label()
    }
}

class AuthenticationException(message: String?) : RuntimeException(message)
class AuthorizationException(message: String?) : RuntimeException(message)
class BadRequestException(message: String?) : RuntimeException(message)
class NotFoundException(message: String?) : RuntimeException(message)