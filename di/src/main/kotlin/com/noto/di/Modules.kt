package com.noto.di

import com.noto.data.repository.LabelRepositoryImpl
import com.noto.data.repository.LibraryRepositoryImpl
import com.noto.data.repository.NotoRepositoryImpl
import com.noto.data.repository.UserRepositoryImpl
import com.noto.database.source.LabelsDSL
import com.noto.database.source.LibrariesDSL
import com.noto.database.source.NotosDSL
import com.noto.database.source.UserDao
import com.noto.domain.interactor.label.*
import com.noto.domain.interactor.library.*
import com.noto.domain.interactor.noto.*
import com.noto.domain.interactor.user.*
import com.noto.database.connectDatabase
import org.koin.dsl.module

fun initDB() {
    connectDatabase()
}

val repositoryModule = module {

    single { LibraryRepositoryImpl(get<LibrariesDSL>()) }

    single { UserRepositoryImpl(get<UserDao>()) }

    single { NotoRepositoryImpl(get<NotosDSL>()) }

    single { LabelRepositoryImpl(get<LabelsDSL>()) }

}

val dataSourceModule = module {

    single { UserDao() }

    single { LibrariesDSL() }
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

val labelUseCasesModule = module {

    single { LabelUseCases(get(), get(), get(), get(), get()) }

    single { CreateLabel(get<LabelRepositoryImpl>()) }

    single { DeleteLabel(get<LabelRepositoryImpl>()) }

    single { UpdateLabel(get<LabelRepositoryImpl>()) }

    single { GetLabelById(get<LabelRepositoryImpl>()) }

    single { GetLabels(get<LabelRepositoryImpl>()) }
}