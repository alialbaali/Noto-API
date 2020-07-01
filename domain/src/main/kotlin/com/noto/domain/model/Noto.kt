package com.noto.domain.model


data class Noto(

    val notoId: Long = 0L,

    val libraryId: Long,

    val notoTitle: String,

    val notoBody: String,

    val notoPosition: Int,
    
    val notoIsStarred: Boolean = false
)
