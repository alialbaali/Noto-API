package com.noto.app.controller

import com.noto.app.service.UserService
import com.noto.app.util.hash
import com.noto.app.util.respond
import com.noto.domain.model.User
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.routing.Routing
import io.ktor.routing.post
import io.ktor.routing.route
import io.ktor.util.KtorExperimentalAPI

@KtorExperimentalAPI
fun Routing.user(userService: UserService) {

    route("/user") {

        post("/create") {
            val user = call.receive<User>()

            val token = userService.createUser(user)

            call.respond(HttpStatusCode.Created, true, data = token)
        }

        post("/login") {
            val user = call.receive<User>()

            val token = userService.loginUser(user)

            call.respond(HttpStatusCode.OK, true, data = token)
        }
    }
}