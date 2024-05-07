package io.srujangajul

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.srujangajul.plugins.*
import io.srujangajul.repository.UserRepository
import io.srujangajul.routing.configureRouting
import io.srujangajul.service.JwtService
import io.srujangajul.service.UserService

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    val userRepository = UserRepository()
    val userService = UserService(userRepository)
    val jwtService = JwtService(this, userService)

    configureSerialization()
    configureSecurity(jwtService)
    configureRouting(userService, jwtService)
}
