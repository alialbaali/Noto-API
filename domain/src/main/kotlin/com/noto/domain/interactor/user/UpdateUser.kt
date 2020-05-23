package com.noto.domain.interactor.user

import com.noto.domain.model.User
import com.noto.domain.repository.UserRepository

class UpdateUser(private val userRepository: UserRepository) {

    suspend operator fun invoke(userId : Long, user: User) = userRepository.updateUser(userId, user)

}