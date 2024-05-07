package io.srujangajul.routing

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.srujangajul.routing.request.UserRequest
import io.srujangajul.service.UserService

fun Route.userRoute(
    userService: UserService
) {
    post {
        val userRequest = call.receive<UserRequest>()
        val createdUser =
            userService.save(userRequest.toModel(userRequest)) ?: return@post call.respond(HttpStatusCode.BadRequest)

        call.response.header(
            name = "id",
            value = createdUser.id.toString()
        )

        call.respond(HttpStatusCode.Created)

    }
    authenticate {

        get {
            val users = userService.findAll()
            call.respond(
                message = users.map { it.toResponse() }
            )
        }

        get("/{id}") {
            val idParam: String = call.parameters["id"] ?: return@get call.respond(HttpStatusCode.BadRequest)
            val user = userService.findById(id = idParam) ?: return@get call.respond(HttpStatusCode.NotFound)
            call.respond(
                user.toResponse()
            )
        }

    }
}