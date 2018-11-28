package com.mason.hoodie.ioc

import com.mason.hoodie.data.MavenRepository
import com.mason.hoodie.presentation.MainViewModel
import org.koin.android.viewmodel.experimental.builder.viewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

/**
 * Created by mason-hong on 28/11/2018.
 */
val repositoryModule = module {
    single { MavenRepository() }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}