package com.alialbaali.noto.domain.model


data class Label(

    val labelId: Long = 0L,

    val labelTitle: String,

    val notoColor: NotoColor
)

data class NotoLabel(
    val notoLabelId: Long,

    val notoId: Long,

    val labelId: Long
)