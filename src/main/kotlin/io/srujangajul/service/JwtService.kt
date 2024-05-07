package io.srujangajul.service

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.server.application.*
import io.ktor.server.auth.jwt.*
import io.srujangajul.routing.request.LoginRequest
import java.util.*

class JwtService (
    private val application: Application,
    private val userService: UserService
) {
    val jwtAudience = "jwt-audience"
    val jwtDomain = "http://localhost/"
    val jwtRealm = "my-realm"
    val jwtSecret = "secret"

    val jwtVerifier: JWTVerifier =
        JWT
            .require(Algorithm.HMAC256(jwtSecret))
            .withAudience(jwtAudience)
            .withIssuer(jwtDomain)
            .build()

    fun createJwtToken(loginRequest: LoginRequest): String? {
        val foundUser = userService.findByUsername(loginRequest.username)

        return if (foundUser != null && foundUser.password == loginRequest.password) {
            JWT
                .create()
                .withAudience(jwtAudience)
                .withIssuer(jwtDomain)
                .withClaim("username", foundUser.username)
                .withExpiresAt(Date(System.currentTimeMillis() + 3_600_000))
                .sign(Algorithm.HMAC256(jwtSecret))
        } else null
    }

    fun customValidator(jwtCredential: JWTCredential): JWTPrincipal? {
        val username = extractUsername(jwtCredential)
        val userFound = username?.let(userService::findByUsername)

        return userFound?.let {
            if(audienceMatches(jwtCredential)){
                JWTPrincipal(jwtCredential.payload)
            } else null
        }
    }

    private fun audienceMatches(jwtCredential: JWTCredential): Boolean {
        return jwtCredential.payload.audience.contains(jwtAudience)
    }

    private fun extractUsername(jwtCredential: JWTCredential): String? =
        jwtCredential.payload.getClaim("username").asString()

}