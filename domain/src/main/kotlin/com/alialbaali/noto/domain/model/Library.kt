package com.alialbaali.noto.domain.model

import java.time.OffsetDateTime
import java.time.ZoneId

data class Library(

    val libraryId: Long = 0L,

    var libraryTitle: String,

    var libraryPosition: Int,

    var notoColor: NotoColor,

    var notoIcon: NotoIcon,

    var sortType: SortType = SortType.DESC,

    var sortMethod: SortMethod = SortMethod.CreationDate,

    val libraryCreationDate: OffsetDateTime = OffsetDateTime.now(ZoneId.systemDefault())
)