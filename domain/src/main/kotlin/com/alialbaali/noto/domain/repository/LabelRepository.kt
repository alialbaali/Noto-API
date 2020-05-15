package com.alialbaali.noto.domain.repository

import com.alialbaali.noto.domain.model.Label

interface LabelRepository {

    suspend fun createLabel(label: Label)

    suspend fun updateLabel(label: Label)

    suspend fun getLabels() : Result<List<Label>>

    suspend fun getLabel(labelId: Long): Result<Label>

    suspend fun deleteLabel(labelId: Long)
}