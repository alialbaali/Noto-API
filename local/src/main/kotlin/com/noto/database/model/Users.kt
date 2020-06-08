package com.noto.database.model

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.jodatime.date
import org.joda.time.DateTime

object Users : Table(name = "users") {
    override val primaryKey: PrimaryKey
        get() = PrimaryKey(userId, userEmail)

    val userId = long("user_id").autoIncrement()

    val userDisplayName = varchar("user_display_name", 32)

    val userEmail = varchar("user_email", 64)

    val userPassword = varchar("user_password", 256)

    val userCreationDate = date("user_creation_date").default(DateTime.now())
}

