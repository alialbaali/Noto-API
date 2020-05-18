package com.noto.database.model

import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.jodatime.datetime
import org.joda.time.DateTime
import org.joda.time.DateTimeZone

object Notos : Table("notos") {
    override val primaryKey: PrimaryKey?
        get() = super.primaryKey

    val notoId = long("noto_id").autoIncrement()

    val libraryId = long("library_id").references(
        Libraries.libraryId,
        ReferenceOption.CASCADE,
        ReferenceOption.CASCADE,
        "library_id"
    )

    val notoTitle = text("noto_title")

    val notoBody = text("noto_body")

    val notoPosition = integer("noto_position")

    val notoCreationDate = datetime("noto_creation_date").default(DateTime.now(DateTimeZone.getDefault()))

    val notoIsStarred = bool("noto_is_starred").default(false)

    val notoReminder = datetime("noto_reminder").nullable()
}

