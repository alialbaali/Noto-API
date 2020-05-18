package com.noto.domain.interactor.label

import com.noto.domain.model.Label
import com.noto.domain.model.Library
import com.noto.domain.repository.LabelRepository
import com.noto.domain.repository.LibraryRepository

class UpdateLabel(private val labelRepository: LabelRepository) {

    suspend operator fun invoke(label: Label) = labelRepository.updateLabel(label)

}