package com.mason.hoodie.ioc

import com.mason.hoodie.data.remote.MavenRepository
import com.mason.hoodie.presentation.SearchViewModel
import org.koin.dsl.module.module

/**
 * Created by mason-hong on 28/11/2018.
 */
val repositoryModule = module {
    single { MavenRepository() }
}

val viewModelModule = module {
    factory { SearchViewModel(get()) }
}