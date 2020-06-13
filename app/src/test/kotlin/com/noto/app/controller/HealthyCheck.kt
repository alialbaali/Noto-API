package com.noto.app.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.noto.app.main
import com.noto.domain.model.User
import com.noto.domain.schema.ResponseSchema
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.setBody
import io.ktor.server.testing.withTestApplication
import io.ktor.util.KtorExperimentalAPI
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

private val objectMapper = jacksonObjectMapper()

fun Any.toJson(): String = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(this)

@KtorExperimentalAPI
class HealthyCheck {


    private val healthResponse = ResponseSchema(true, null, "Healthy").toJson()

    private val user =
        User(userDisplayName = "Ali", userEmail = "aliabaali@gmail.com", userPassword = "ThisPassword0!3").toJson()


    @Test
    fun test_health_check(): Unit = withTestApplication({ main(testing = true) }) {

        with(handleRequest(HttpMethod.Get, "/")) {

            assertEquals(HttpStatusCode.OK, response.status())
            assertEquals(healthResponse, response.content)

        }

    }


    @Test
    fun test_create_user(): Unit = withTestApplication({ main(testing = false) }) {
        handleRequest {
            method = HttpMethod.Post
            uri = "/user/create"
            setBody(user)
        }.apply {
            println(response.content)
//                println(response.status())
//                assertEquals(HttpStatusCode.OK, response.status())
        }
    }

//    @KtorExperimentalAPI
//    @Test
//    fun testCreateUser() = withTestApplication {
//        fun testRequests() = withTestApplication(Application::module) {
////            with(handleRequest(HttpMethod.Get, "/")) {
////                assertEquals(HttpStatusCode.OK, response.status())
////                assertEquals("Hello from Ktor Testable sample application", response.content)
////            }
////            with(handleRequest(HttpMethod.Get, "/index.html")) {
////                assertFalse(requestHandled)
//            }
//        }

//    }

}