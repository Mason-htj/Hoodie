package com.mason.hoodie.ui

import android.app.Application
import com.mason.hoodie.ioc.databaseModule
import com.mason.hoodie.ioc.repositoryModule
import com.mason.hoodie.ioc.viewModelModule
import org.koin.android.ext.android.startKoin

/**
 * Created by mason-hong on 28/11/2018.
 */
class HoodieApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(
            repositoryModule,
            databaseModule,
            viewModelModule
        ))
    }
}