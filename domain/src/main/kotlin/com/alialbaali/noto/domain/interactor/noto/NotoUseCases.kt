package com.alialbaali.noto.domain.interactor.noto


class NotoUseCases(
    val createNoto: CreateNoto,
    val updateNoto: UpdateNoto,
    val deleteNoto: DeleteNoto,
    val getNotoById: GetNotoById,
    val getNotos: GetNotos
)