package com.noto.database.source

import com.noto.data.source.UserDataSource
import com.noto.database.model.Users
import com.noto.domain.model.User
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

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

    override suspend fun updateUser(userId: Long, user: User) {
        return transaction {

            Users.update(where = { Users.userId eq userId }) {
                it[userDisplayName] = user.userDisplayName
                it[username] = user.username.toLowerCase()
                it[userPassword] = user.userPassword
            }
        }
    }

    override suspend fun getUserByUsername(username: String): User? {
        return transaction {

            Users.select { Users.username eq username.toLowerCase() }
                .map {
                    User(
                        it[Users.userId],
                        it[Users.userDisplayName],
                        it[Users.username],
                        ""
                    )
                }.firstOrNull()

        }
    }

    override suspend fun getUserById(userId: Long): User? {
        return transaction {

            Users.select { Users.userId eq userId }
                .map {
                    User(
                        it[Users.userId],
                        it[Users.userDisplayName],
                        it[Users.username],
                        ""
                    )
                }.firstOrNull()

        }
    }

    override suspend fun delete(userId: Long) {
        transaction {
            Users.deleteWhere { Users.userId eq userId }
        }
    }
}