package io.srujangajul.service

import io.srujangajul.model.User
import io.srujangajul.repository.UserRepository
import io.srujangajul.routing.request.UserRequest
import java.util.*

class UserService (
    private val userRepository: UserRepository
) {
    fun findAll(): List<User> = userRepository.findAll()

    fun findById(id: String): User? =
        userRepository.findById(
            id = UUID.fromString(id)
        )

    fun findByUsername(username: String): User? =
        userRepository.findByName(username)

    fun save(user: User):User? {
        val userFound = findByUsername(user.username)

        return if(userFound == null){
            userRepository.save(user)
            user
        }
        else{
            null
        }
    }
}