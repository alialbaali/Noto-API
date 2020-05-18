package com.alialbaali.noto.domain.interactor.library

class LibraryUseCases(
    val getLibraries: GetLibraries,
    val createLibrary: CreateLibrary,
    val updateLibrary: UpdateLibrary,
    val deleteLibrary: DeleteLibrary
)