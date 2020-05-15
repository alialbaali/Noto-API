package com.alialbaali.noto.domain.model

import java.time.OffsetDateTime
import java.time.ZoneId


data class Noto(

    val notoId: Long = 0L,

    var libraryId: Long,

    var notoTitle: String,

    var notoPosition: Int,

    val notoCreationDate: OffsetDateTime = OffsetDateTime.now(ZoneId.systemDefault()),

    var notoIsStarred: Boolean = false,

    val notoSchedule: OffsetDateTime? = null
)