package com.noto.database.source

import com.noto.data.source.LabelDataSource
import com.noto.database.model.Labels
import com.noto.domain.model.Label
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class LabelsDSL : LabelDataSource {
    override suspend fun createLabel(label: Label) {
        transaction {
            Labels.insert {
                it[labelId] = label.labelId
                it[labelTitle] = label.labelTitle
                it[notoColor] = label.notoColor
            }
        }
    }

    override suspend fun getLabels(): List<Label> {
        return transaction {
            Labels.selectAll().map {
                Label(
                    it[Labels.labelId],
                    it[Labels.labelTitle],
                    it[Labels.notoColor]
                )
            }
        }
    }

    override suspend fun getLabelById(labelId: Long): Label {
        return transaction {
            Labels.select { Labels.labelId eq labelId }.map {
                Label(
                    it[Labels.labelId],
                    it[Labels.labelTitle],
                    it[Labels.notoColor]
                )
            }.first()
        }
    }

    override suspend fun updateLabel(label: Label) {
        transaction {
            Labels.update {
                it[labelId] = label.labelId
                it[labelTitle] = label.labelTitle
                it[notoColor] = label.notoColor
            }
        }
    }

    override suspend fun deleteLabel(labelId: Long) {
        transaction {
            Labels.deleteWhere { Labels.labelId eq labelId }
        }
    }

}