package com.alialbaali.noto.domain.model

import org.joda.time.DateTime


data class Library(

    val libraryId: Long = 0L,

    val libraryTitle: String,

    val libraryPosition: Int,

    val notoColor: NotoColor,

    val notoIcon: NotoIcon,

    val sortType: SortType = SortType.DESC,

    val sortMethod: SortMethod = SortMethod.CreationDate,

    val libraryCreationDate: DateTime = DateTime.now()
)