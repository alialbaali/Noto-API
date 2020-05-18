package com.noto.data.source

import com.noto.domain.model.Label

interface LabelDataSource {

    suspend fun createLabel(label: Label)

    suspend fun getLabels(): List<Label>

    suspend fun getLabelById(labelId : Long): Label

    suspend fun updateLabel(label: Label)

    suspend fun deleteLabel(labelId: Long)
}