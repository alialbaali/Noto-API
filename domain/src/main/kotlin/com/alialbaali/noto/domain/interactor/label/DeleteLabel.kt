package com.alialbaali.noto.domain.interactor.label

import com.alialbaali.noto.domain.repository.LabelRepository

class DeleteLabel(private val labelRepository: LabelRepository) {

    suspend operator fun invoke(labelId: Long) = labelRepository.deleteLabel(labelId)

}