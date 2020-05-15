package com.alialbaali.noto.domain.interactor.user

import com.alialbaali.noto.domain.repository.UserRepository

class GetUsers(private val userRepository: UserRepository) {

    suspend operator fun invoke() = userRepository.getUsers()

}