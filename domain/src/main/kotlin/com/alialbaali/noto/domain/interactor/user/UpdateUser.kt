package com.alialbaali.noto.domain.interactor.user

import com.alialbaali.noto.domain.model.User
import com.alialbaali.noto.domain.repository.UserRepository

class UpdateUser(private val userRepository: UserRepository) {

    suspend operator fun invoke(user: User) = userRepository.updateUser(user)

}