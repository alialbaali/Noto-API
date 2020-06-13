package com.noto.database.model

import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.jodatime.date
import org.jetbrains.exposed.sql.jodatime.datetime

object Notos : Table("notos") {
    override val primaryKey: PrimaryKey?
        get() = PrimaryKey(notoId)

    val notoId = long("noto_id").autoIncrement()

    val notoClientId = long("noto_client_id")

    val libraryId = reference("library_id", Libraries.libraryId, ReferenceOption.CASCADE, ReferenceOption.CASCADE).index()

    val notoTitle = text("noto_title")

    val notoBody = text("noto_body")

    val notoPosition = integer("noto_position")

    val notoCreationDate = date("noto_creation_date")

    val notoIsStarred = bool("noto_is_starred").default(false)

    val notoReminderDate = datetime("noto_reminder_date").nullable()
}

