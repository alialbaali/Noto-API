package com.alialbaali.noto.domain.interactor.user

import com.alialbaali.noto.domain.model.User
import com.alialbaali.noto.domain.repository.UserRepository

class CreateUser(private val userRepository: UserRepository) {

    suspend operator fun invoke(user: User) =  userRepository.createUser(user)

}