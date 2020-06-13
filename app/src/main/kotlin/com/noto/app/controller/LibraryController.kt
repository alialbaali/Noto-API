package com.noto.app.controller

import com.noto.app.BadRequestException
import com.noto.app.NotFoundException
import com.noto.app.service.LibraryService
import com.noto.app.util.respond
import com.noto.domain.model.Library
import io.ktor.application.call
import io.ktor.auth.authenticate
import io.ktor.auth.authentication
import io.ktor.auth.jwt.JWTPrincipal
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.request.receiveOrNull
import io.ktor.response.header
import io.ktor.routing.*

fun Routing.library(libraryService: LibraryService) {

    authenticate {

        route("/libraries") {

            get {

                val userId = call.authentication.principal<JWTPrincipal>()?.payload?.getClaim("userId")?.asLong()
                    ?: throw BadRequestException("Missing claims")

                val libraries = libraryService.getLibraries(userId)

                call.respond(HttpStatusCode.OK, true, data = libraries)

            }

            post {

                val userId = call.authentication.principal<JWTPrincipal>()?.payload?.getClaim("userId")?.asLong()
                    ?: throw BadRequestException("Missing claims")

                val library = call.receiveOrNull<Library>() ?: throw BadRequestException("Missing body")

                val data = libraryService.createLibrary(userId, library)

                call.respond(HttpStatusCode.OK, true, data = data)

            }

            patch {

                val userId = call.authentication.principal<JWTPrincipal>()?.payload?.getClaim("userId")?.asLong()
                    ?: throw BadRequestException("Missing claims")

                val library = call.receiveOrNull<Library>() ?: throw BadRequestException("Missing body")

                val data = libraryService.updateLibrary(userId, library)

                call.respond(HttpStatusCode.OK, true, data = data)
            }

            delete("/{id}") {

                val userId = call.authentication.principal<JWTPrincipal>()?.payload?.getClaim("userId")?.asLong()
                    ?: throw BadRequestException("Missing claims")

                val libraryId = call.parameters["id"]?.toLong() ?: throw BadRequestException("Missing ID")

                val data = libraryService.deleteLibrary(userId, libraryId)

                call.respond(HttpStatusCode.NoContent, true, data = data)

            }
        }
    }
}