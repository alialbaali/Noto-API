package com.noto.database.model

import org.jetbrains.exposed.sql.Table

object NotoLabels : Table("noto_labels") {
    override val primaryKey: PrimaryKey?
        get() = PrimaryKey(notoLabelId, name = "noto_label_id")

    val notoLabelId = long("noto_label_id").autoIncrement()

    val notoId = long("noto_id").references(Notos.notoId)

    val labelId = long("label_id").references(Labels.labelId)
}