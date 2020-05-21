package com.noto.app.controller

import com.noto.app.AuthorizationException
import com.noto.app.NotFoundException
import com.noto.app.service.LibraryService
import com.noto.app.util.respond
import com.noto.domain.model.Library
import io.ktor.application.call
import io.ktor.auth.authenticate
import io.ktor.auth.authentication
import io.ktor.auth.jwt.JWTPrincipal
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.routing.*

fun Routing.library(libraryService: LibraryService) {

    authenticate {

        route("/libraries") {

            get {
                val userId = call.authentication.principal<JWTPrincipal>()!!.payload.getClaim("userId").asLong()

                val libraries = libraryService.getLibraries(userId)

                call.respond(HttpStatusCode.OK, true, data = libraries)
            }

            post {
                val userId = call.authentication.principal<JWTPrincipal>()!!.payload.getClaim("userId").asLong()

                val library = call.receive<Library>()


                val data = libraryService.createLibrary(userId, library)

                call.respond(HttpStatusCode.OK, true, data = data)

            }

            patch {
                val userId = call.authentication.principal<JWTPrincipal>()!!.payload.getClaim("userId").asLong()

                val library = call.receive<Library>()


                val data = libraryService.updateLibrary(userId, library)

                call.respond(HttpStatusCode.OK, true, data = data)
            }

            delete("/{id}") {
                val userId = call.authentication.principal<JWTPrincipal>()!!.payload.getClaim("userId").asLong()

                val libraryId = call.parameters["id"]?.toLong() ?: throw NotFoundException("Invalid ID")


                val data = libraryService.deleteLibrary(userId, libraryId)

                call.respond(HttpStatusCode.OK, true, data = data)

            }
        }
    }
}