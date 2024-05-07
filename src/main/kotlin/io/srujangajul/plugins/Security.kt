package io.srujangajul.plugins

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import io.srujangajul.service.JwtService

fun Application.configureSecurity(
    jwtService: JwtService
) {
    // Please read the jwt property from the config file if you are using EngineMain

    authentication {
        jwt{
            realm = jwtService.jwtRealm
            verifier(jwtService.jwtVerifier)
            validate {
                jwtCredential -> jwtService.customValidator(jwtCredential)
            }
        }
    }
}
