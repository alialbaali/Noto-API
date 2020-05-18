package com.noto.data.repository

import com.noto.data.source.LabelDataSource
import com.noto.domain.model.Label
import com.noto.domain.repository.LabelRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LabelRepositoryImpl(private val local: LabelDataSource) : LabelRepository {

    override suspend fun createLabel(label: Label) {
        return withContext(Dispatchers.IO) {
//            try {
//                Result.success(local.createLabel(label))
//            } catch (e: Throwable) {
//                Result.failure<Throwable>(e)
//            }
        }
    }

    override suspend fun updateLabel(label: Label) {
        return withContext(Dispatchers.IO) {
//            try {
//                Result.success(local.updateLabel(label))
//            } catch (e: Throwable) {
//                Result.failure<Throwable>(e)
//            }
        }
    }

    override suspend fun getLabels(): Result<List<Label>> {
        return withContext(Dispatchers.IO) {
//            try {
                Result.success(local.getLabels())
//            } catch (e: Throwable) {
//                Result.failure(e)
//            }
        }
    }

    override suspend fun getLabel(labelId: Long): Result<Label> {
        return withContext(Dispatchers.IO) {
//            try {
                Result.success(local.getLabelById(labelId))
//            } catch (e: Throwable) {
//                Result.failure(e)
//            }
        }
    }

    override suspend fun deleteLabel(labelId: Long) {
        withContext(Dispatchers.IO) {
//            try {
//                Result.success(local.deleteLabel(labelId))
//            } catch (e: Throwable) {
//                Result.failure(e)
//            }
        }
    }
}