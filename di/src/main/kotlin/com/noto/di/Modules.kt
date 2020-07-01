package com.noto.di

import com.noto.data.repository.LibraryRepositoryImpl
import com.noto.data.repository.NotoRepositoryImpl
import com.noto.data.repository.UserRepositoryImpl
import com.noto.database.connectDatabase
import com.noto.database.source.LibraryDao
import com.noto.database.source.NotoDao
import com.noto.database.source.UserDao
import com.noto.domain.interactor.library.*
import com.noto.domain.interactor.noto.*
import com.noto.domain.interactor.user.*
import org.koin.dsl.module

fun initDB(testing: Boolean = false) {
    connectDatabase(testing)
}

val repositoryModule = module {

    single { LibraryRepositoryImpl(get<LibraryDao>()) }

    single { UserRepositoryImpl(get<UserDao>()) }

    single { NotoRepositoryImpl(get<NotoDao>()) }

}

val dataSourceModule = module {

    single { UserDao() }

    single { LibraryDao() }

    single { NotoDao() }
}

val libraryUseCasesModule = module {

    single { LibraryUseCases(get(), get(), get(), get()) }

    single { CreateLibrary(get<LibraryRepositoryImpl>()) }

    single { DeleteLibrary(get<LibraryRepositoryImpl>()) }

    single { GetLibraries(get<LibraryRepositoryImpl>()) }

    single { UpdateLibrary(get<LibraryRepositoryImpl>()) }

}

val userUseCasesModule = module {

    single { UserUseCases(get(), get(), get(), get(), get()) }

    single { CreateUser(get<UserRepositoryImpl>()) }

    single { DeleteUser(get<UserRepositoryImpl>()) }

    single { UpdateUser(get<UserRepositoryImpl>()) }

    single { LoginUser(get<UserRepositoryImpl>()) }

    single { CheckUserId(get<UserRepositoryImpl>()) }
}


val notoUseCasesModule = module {

    single { NotoUseCases(get(), get(), get(), get()) }

    single { CreateNoto(get<NotoRepositoryImpl>()) }

    single { DeleteNoto(get<NotoRepositoryImpl>()) }

    single { UpdateNoto(get<NotoRepositoryImpl>()) }

    single { GetNotos(get<NotoRepositoryImpl>()) }
}