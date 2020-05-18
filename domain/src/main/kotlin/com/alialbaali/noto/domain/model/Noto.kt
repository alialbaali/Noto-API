package com.alialbaali.noto.domain.model

import org.joda.time.DateTime
import java.time.OffsetDateTime
import java.time.ZoneId


data class Noto(

    val notoId: Long = 0L,

    val libraryId: Long,

    val notoTitle: String,

    val notoBody : String,

    val notoPosition: Int,

    val notoCreationDate: DateTime = DateTime.now(),

    val notoIsStarred: Boolean = false,

    val notoSchedule: DateTime? = null
)