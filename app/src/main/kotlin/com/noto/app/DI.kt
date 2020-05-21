package com.noto.app

import com.noto.app.service.UserService
import org.koin.dsl.module

val appModule = module {

    single { UserService(get()) }

}