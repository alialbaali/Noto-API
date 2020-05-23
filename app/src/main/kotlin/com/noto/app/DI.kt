package com.noto.app

import com.noto.app.service.LibraryService
import com.noto.app.service.NotoService
import com.noto.app.service.UserService
import io.ktor.util.KtorExperimentalAPI
import org.koin.dsl.module

@KtorExperimentalAPI
val appModule = module {

    single { UserService(get()) }

//    single { LibraryService(get()) }
//
//    single { NotoService(get()) }

}