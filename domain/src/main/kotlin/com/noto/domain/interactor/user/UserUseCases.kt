package com.noto.domain.interactor.user

class UserUseCases(
    val createUser: CreateUser,
    val deleteUser: DeleteUser,
    val loginUser: LoginUser,
    val updateUser: UpdateUser,
    val checkUserId: CheckUserId
)