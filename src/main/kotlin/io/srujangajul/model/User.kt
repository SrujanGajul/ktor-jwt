package io.srujangajul.model

import io.srujangajul.routing.response.UserResponse
import java.util.UUID

data class User (
    val id:UUID,
    val username: String,
    val password: String,
){
    fun toResponse():UserResponse {
        return UserResponse(
            id = this.id,
            username = this.username
        )
    }


}