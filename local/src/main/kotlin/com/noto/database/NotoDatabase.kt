package com.noto.database

import com.noto.database.model.Libraries
import com.noto.database.model.Notos
import com.noto.database.model.Users
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

private val DB_NAME = System.getenv("DB_NAME")
private val TEST_DB_NAME = System.getenv("TEST_DB_NAME")
private val URL = System.getenv("DB_URL")
private val DRIVER = System.getenv("DB_DRIVER")
private val USER = System.getenv("DB_USERNAME")
private val PASSWORD = System.getenv("DB_PASSWORD")


fun connectDatabase(testing: Boolean = false) {

    Database.connect(
        URL.plus(if (testing) TEST_DB_NAME else DB_NAME),
        DRIVER,
        USER,
        PASSWORD
    )

    transaction {
        SchemaUtils.createMissingTablesAndColumns(Users, Libraries, Notos)
    }
}