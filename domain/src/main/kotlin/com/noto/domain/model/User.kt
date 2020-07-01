package com.noto.domain.model

data class User(
    val userId: Long = 0L,

    val userDisplayName: String,

    val userEmail: String,

    val userPassword: String
)
