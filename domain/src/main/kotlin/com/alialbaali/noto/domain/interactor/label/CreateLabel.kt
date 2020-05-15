package com.alialbaali.noto.domain.interactor.label

import com.alialbaali.noto.domain.model.Label
import com.alialbaali.noto.domain.repository.LabelRepository

class CreateLabel(private val labelRepository: LabelRepository) {

    suspend operator fun invoke(label: Label) = labelRepository.createLabel(label)

}