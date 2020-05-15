package com.alialbaali.noto.domain.interactor.label

import com.alialbaali.noto.domain.model.Label
import com.alialbaali.noto.domain.model.Library
import com.alialbaali.noto.domain.repository.LabelRepository
import com.alialbaali.noto.domain.repository.LibraryRepository

class UpdateLabel(private val labelRepository: LabelRepository) {

    suspend operator fun invoke(label: Label) = labelRepository.updateLabel(label)

}