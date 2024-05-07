package io.srujangajul.routing.request

import io.srujangajul.model.User
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class UserRequest(
    val username: String,
    val password: String
) {
    fun toModel(userRequest: UserRequest): User = User(
        id = UUID.randomUUID(),
        username = userRequest.username,
        password = userRequest.password
    )
}