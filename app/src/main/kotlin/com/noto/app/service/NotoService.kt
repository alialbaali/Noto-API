package com.noto.app.service

import com.noto.domain.interactor.noto.NotoUseCases
import com.noto.domain.model.Noto

class NotoService(private val notoUseCases: NotoUseCases) {


    suspend fun createNoto(userId: Long, noto: Noto): Noto {

//        return notoUseCases.createNoto()
        TODO()

    }

}