package com.noto.database.source

import com.noto.data.source.NotoDataSource
import com.noto.domain.model.Noto
import com.noto.database.model.Notos
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction


class NotosDSL : NotoDataSource {
    override suspend fun createNoto(noto: Noto) {
        transaction {
            Notos.insert {
                it[notoId] = noto.notoId
                it[libraryId] = noto.libraryId
                it[notoTitle] = noto.notoTitle
                it[notoBody] = noto.notoBody
                it[notoPosition] = notoPosition
                it[notoIsStarred] = noto.notoIsStarred
            }
        }
    }

    override suspend fun getNotos(libraryId: Long): List<Noto> {
        return transaction {
            Notos.selectAll().map {
                Noto(
                    it[Notos.notoId],
                    it[Notos.libraryId],
                    it[Notos.notoTitle],
                    it[Notos.notoBody],
                    it[Notos.notoPosition]
                )
            }
        }
    }

    override suspend fun getNotoById(notoId: Long): Noto {
        return transaction {
            Notos.select { Notos.libraryId eq notoId }.map {
                Noto(
                    it[Notos.notoId],
                    it[Notos.libraryId],
                    it[Notos.notoTitle],
                    it[Notos.notoBody],
                    it[Notos.notoPosition]
                )
            }
        }.first()
    }

    override suspend fun countLibraryNotos(libraryId: Long): Int {
        TODO("Not yet implemented")
    }

    override suspend fun countNotos(): Int {
        TODO("Not yet implemented")
    }

    override suspend fun deleteNoto(notoId: Long) {
        transaction {
            Notos.deleteWhere { Notos.notoId eq notoId }
        }
    }

    override suspend fun updateNoto(noto: Noto) {
        transaction {
            Notos.update {
                it[notoId] = noto.notoId
                it[libraryId] = noto.libraryId
                it[notoTitle] = noto.notoTitle
                it[notoBody] = noto.notoBody
                it[notoPosition] = notoPosition
                it[notoIsStarred] = noto.notoIsStarred
            }
        }
    }

}