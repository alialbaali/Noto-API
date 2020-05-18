package com.noto.database.model

import com.noto.domain.model.NotoColor
import org.jetbrains.exposed.sql.*

object Labels : Table("labels") {
    val labelId = long("label_id").autoIncrement()

    val labelTitle = varchar("label_title", 32)

    val notoColor = enumeration("noto_color", NotoColor::class)
}

