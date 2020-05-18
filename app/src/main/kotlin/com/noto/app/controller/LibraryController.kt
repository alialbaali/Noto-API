package com.noto.app.controller

import com.noto.app.AuthorizationException
import com.noto.app.NotFoundException
import com.noto.domain.interactor.library.LibraryUseCases
import com.noto.domain.model.Library
import com.noto.app.util.respond
import io.ktor.application.call
import io.ktor.auth.authenticate
import io.ktor.auth.authentication
import io.ktor.auth.jwt.JWTPrincipal
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.routing.*
import org.joda.time.DateTime
import org.koin.ktor.ext.inject

fun Routing.library(libraryUseCases: LibraryUseCases = inject<LibraryUseCases>().value) {

    authenticate {

        route("/libraries") {

            get {
                val userId = call.authentication.principal<JWTPrincipal>()!!.payload.getClaim("userId").asLong()
                val libraries = libraryUseCases.getLibraries(userId).getOrElse {
                    throw AuthorizationException(it.message)
                }

                call.respond(HttpStatusCode.OK, true, data = libraries)
                println("DATE DATE DATE     " + DateTime.now())
            }

            post {
                val userId = call.authentication.principal<JWTPrincipal>()!!.payload.getClaim("userId").asLong()
                val library = call.receive<Library>()

                libraryUseCases.createLibrary(userId, library).onSuccess {
                    call.respond(HttpStatusCode.Created, true, data = library)
                }.onFailure {
                    throw AuthorizationException(it.message)
                }
            }

            patch {
                val userId = call.authentication.principal<JWTPrincipal>()!!.payload.getClaim("userId").asLong()
                val library = call.receive<Library>()

                val data = libraryUseCases.updateLibrary(userId, library).getOrElse {
                    throw AuthorizationException(it.message)
                }

                call.respond(HttpStatusCode.OK, true, data = data)
            }

            delete("/{id}") {

                val userId = call.authentication.principal<JWTPrincipal>()!!.payload.getClaim("userId").asLong()
                val libraryId = call.parameters["id"]?.toLong() ?: throw NotFoundException("Invalid ID")

                val data = libraryUseCases.deleteLibrary(userId, libraryId).getOrElse {
                    throw AuthorizationException(it.message)
                }

                call.respond(HttpStatusCode.OK, true, data = data)

            }
        }
    }
}