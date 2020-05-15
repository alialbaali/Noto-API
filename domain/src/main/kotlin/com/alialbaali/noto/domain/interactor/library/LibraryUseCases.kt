package com.alialbaali.noto.domain.interactor.library

class LibraryUseCases(
    val createLibrary: CreateLibrary,
    val deleteLibrary: DeleteLibrary,
    val updateLibrary: UpdateLibrary,
    val getLibraries: GetLibraries,
    val getLibrary: GetLibrary
)