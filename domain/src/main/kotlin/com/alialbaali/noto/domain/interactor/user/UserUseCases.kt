package com.alialbaali.noto.domain.interactor.user

class UserUseCases(
    val createUser: CreateUser,
    val deleteUser: DeleteUser,
    val getUserById: GetUserById,
    val getUsers: GetUsers,
    val updateUser: UpdateUser
)