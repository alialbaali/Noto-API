package com.noto.app.controller

import com.noto.app.BadRequestException
import com.noto.app.service.NotoService
import com.noto.app.util.respond
import com.noto.domain.model.Noto
import io.ktor.application.call
import io.ktor.auth.authenticate
import io.ktor.auth.authentication
import io.ktor.auth.jwt.JWTPrincipal
import io.ktor.http.HttpStatusCode
import io.ktor.request.receiveOrNull
import io.ktor.routing.*

fun Routing.noto(notoService: NotoService) {

    authenticate {

        route("/notos") {

            get {

                val userId = call.authentication.principal<JWTPrincipal>()?.payload?.getClaim("userId")?.asLong()
                    ?: throw BadRequestException("Missing claims")

                val notos = notoService.getNotos(userId)

                call.respond(HttpStatusCode.OK, true, null, notos)

            }

            post {

                val userId = call.authentication.principal<JWTPrincipal>()?.payload?.getClaim("userId")?.asLong()
                    ?: throw BadRequestException("Missing claims")

                val noto = call.receiveOrNull<Noto>() ?: throw BadRequestException("Missing body")

                val data = notoService.createNoto(userId, noto)

                call.respond(HttpStatusCode.Created, true, data = data)

            }

            patch {

                val userId = call.authentication.principal<JWTPrincipal>()?.payload?.getClaim("userId")?.asLong()
                    ?: throw BadRequestException("Missing claims")

                val noto = call.receiveOrNull<Noto>() ?: throw BadRequestException("Missing body")

                val data = notoService.updateNoto(userId, noto)

                call.respond(HttpStatusCode.OK, true, data = data)
            }

            delete("{library-id}/{noto-id}") {
                val userId = call.authentication.principal<JWTPrincipal>()?.payload?.getClaim("userId")?.asLong()
                    ?: throw BadRequestException("Missing claims")

                val libraryId = call.parameters["library-id"]?.toLong() ?: throw BadRequestException("Missing ID")

                val notoId = call.parameters["noto-id"]?.toLong() ?: throw BadRequestException("Missing ID")

                val data = notoService.deleteNoto(userId, libraryId, notoId)

                call.respond(HttpStatusCode.NoContent, true, data = data)
            }

        }

    }

}