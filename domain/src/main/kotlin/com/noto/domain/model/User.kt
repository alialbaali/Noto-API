package com.noto.domain.model

import org.joda.time.DateTime

data class User(
    val userId: Long = 0L,

    val userDisplayName: String,

    val userEmail: String,

    val userPassword: String,

    val userCreationDate : DateTime = DateTime.now()
)