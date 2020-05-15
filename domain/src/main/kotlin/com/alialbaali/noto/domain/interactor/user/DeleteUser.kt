package com.alialbaali.noto.domain.interactor.user

import com.alialbaali.noto.domain.repository.UserRepository

class DeleteUser(private val userRepository: UserRepository) {

    suspend operator fun invoke(userId: Long) = userRepository.deleteUser(userId)

}