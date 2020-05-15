package com.alialbaali.noto.domain.interactor.label

import com.alialbaali.noto.domain.model.Label
import com.alialbaali.noto.domain.repository.LabelRepository

class GetLabels(private val labelRepository: LabelRepository) {

    suspend operator fun invoke(): Result<List<Label>> = labelRepository.getLabels()

}