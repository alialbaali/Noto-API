package com.noto.database.model

import com.alialbaali.noto.domain.model.*
import com.noto.domain.model.NotoColor
import com.noto.domain.model.NotoIcon
import com.noto.domain.model.SortMethod
import com.noto.domain.model.SortType
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.jodatime.date

object Libraries : Table("libraries") {
    override val primaryKey: PrimaryKey?
        get() = PrimaryKey(libraryId)

    val libraryId = long("library_id").autoIncrement()

    val libraryClientId  = long("library_client_id")

    val userId = long("user_id").references(
        Users.userId,
        ReferenceOption.CASCADE,
        ReferenceOption.CASCADE,
        "user_id")

    val libraryTitle = varchar("library_title", 32)

    val libraryPosition = integer("library_position")

    val notoColor = enumeration("noto_color", NotoColor::class)

    val notoIcon = enumeration("noto_icon", NotoIcon::class)

    val sortMethod = enumeration("sort_method", SortMethod::class)

    val sortType = enumeration("sort_type", SortType::class)

    val libraryCreationDate = date("library_creation_date")
}


