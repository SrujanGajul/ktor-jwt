package io.srujangajul.routing

import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.srujangajul.service.JwtService
import io.srujangajul.service.UserService

fun Application.configureRouting(
    userService: UserService,
    jwtService: JwtService
) {
    routing {
        route("/api/auth"){
            authRoute(jwtService)
        }

        route("/api/user"){
            userRoute(userService)
        }
    }
}


