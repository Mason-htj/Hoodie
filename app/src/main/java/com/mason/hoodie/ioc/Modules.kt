package com.mason.hoodie.ioc

import android.arch.persistence.room.Room
import com.mason.hoodie.data.local.AppDatabase
import com.mason.hoodie.data.remote.MavenRepository
import com.mason.hoodie.presentation.FavoritesViewModel
import com.mason.hoodie.presentation.SearchViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

/**
 * Created by mason-hong on 28/11/2018.
 */
val repositoryModule = module {
    single { MavenRepository() }
}

val databaseModule = module {
    single {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, "AppDatabase")
                .build()
    }
}

val viewModelModule = module {
    single { SearchViewModel(get(), get()) }
    single { FavoritesViewModel(get(), get()) }
}