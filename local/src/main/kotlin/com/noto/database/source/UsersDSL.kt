package com.noto.database.source

import com.noto.data.source.UserDataSource
import com.noto.database.model.Users
import com.noto.domain.model.User
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class UsersDSL : UserDataSource {
    override suspend fun createUser(user: User) {
        transaction {
            Users.insert {
                it[userDisplayName] = user.userDisplayName
                it[username] = user.username.toLowerCase()
                it[userPassword] = user.userPassword
            }
        }
    }

    override suspend fun delete(userId: Long) {
        Users.deleteWhere { Users.userId eq userId }
    }

    override suspend fun updateUser(user: User) {
        transaction {
            Users.update {
                it[userDisplayName] = user.userDisplayName
                it[username] = user.username.toLowerCase()
                it[userPassword] = user.userPassword
            }
        }
    }

    override suspend fun getUserByUsername(username: String): User? {
        return transaction {
            Users.select { Users.username eq username }.map {
                User(
                    it[Users.userId],
                    it[Users.userDisplayName],
                    it[Users.username],
                    it[Users.userPassword],
                    it[Users.userCreationDate]
                )
            }
        }.firstOrNull()
    }

    override suspend fun getUsers(): List<User> {
        return transaction {
            Users.selectAll().map {
                User(
                    it[Users.userId],
                    it[Users.userDisplayName],
                    it[Users.username],
                    it[Users.userPassword],
                    it[Users.userCreationDate]
                )
            }
        }
    }
}