package com.noto.domain.model


data class Library(

    val libraryId: Long = 0L,

    val libraryTitle: String,

    val libraryPosition: Int,

    val notoColor: NotoColor,

    val notoIcon: NotoIcon,

    val sortType: SortType = SortType.DESC,

    val sortMethod: SortMethod = SortMethod.CreationDate
)