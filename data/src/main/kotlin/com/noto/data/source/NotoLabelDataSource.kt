package com.noto.data.source

import com.noto.domain.model.NotoLabel

interface NotoLabelDataSource {

    suspend fun getNotoLabel(): NotoLabel

    suspend fun createNotoLabel(notoLabel: NotoLabel)

    suspend fun deleteNotoLabel(notoLabelId: Long)

}