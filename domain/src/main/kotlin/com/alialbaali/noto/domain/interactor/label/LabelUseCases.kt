package com.alialbaali.noto.domain.interactor.label

class LabelUseCases(
    val createLabel: CreateLabel,
    val deleteLabel: DeleteLabel,
    val updateLabel: UpdateLabel,
    val getLabelById: GetLabelById,
    val getLabels: GetLabels
)