package com.noto.database.source

import com.noto.data.source.NotoLabelDataSource
import com.noto.domain.model.NotoLabel

class NotoLabelsDSL() : NotoLabelDataSource {
    override suspend fun getNotoLabel(): NotoLabel {
        TODO("Not yet implemented")
    }

    override suspend fun createNotoLabel(notoLabel: NotoLabel) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteNotoLabel(notoLabelId: Long) {
        TODO("Not yet implemented")
    }

}