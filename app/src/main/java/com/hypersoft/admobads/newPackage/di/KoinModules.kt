package com.hypersoft.admobads.newPackage.di

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.hypersoft.admobads.newPackage.ads.natives.data.dataSources.local.DataSourceLocalNative
import com.hypersoft.admobads.newPackage.ads.natives.data.dataSources.remote.DataSourceRemoteNative
import com.hypersoft.admobads.newPackage.ads.natives.data.repositories.RepositoryNative
import com.hypersoft.admobads.newPackage.ads.natives.domain.useCases.UseCaseNative
import com.hypersoft.admobads.newPackage.ads.natives.presentation.viewModels.ViewModelNative
import com.hypersoft.admobads.newPackage.utilities.firebase.RemoteConfiguration
import com.hypersoft.admobads.newPackage.utilities.manager.InternetManager
import com.hypersoft.admobads.newPackage.utilities.manager.SharedPreferencesUtils
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by: Sohaib Ahmed
 * Date: 1/15/2025
 *
 * Links:
 * - LinkedIn: https://linkedin.com/in/epegasus
 * - GitHub: https://github.com/epegasus
 */

class KoinModules {

    private val managerModules = module {
        single { InternetManager(androidContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager) }
    }

    private val utilsModules = module {
        single { SharedPreferencesUtils(androidContext().getSharedPreferences("app_preferences", Application.MODE_PRIVATE)) }
    }

    private val firebaseModule = module {
        single { RemoteConfiguration(get(), get()) }
    }

    private val nativeAdModule = module {
        single { DataSourceLocalNative() }
        single { DataSourceRemoteNative(context = get()) }
        single { RepositoryNative(get(), get()) }
        single { UseCaseNative(get(), get(), get(), get()) }
        viewModel { ViewModelNative(get()) }
    }

    val modulesList = listOf(utilsModules, managerModules, firebaseModule, nativeAdModule)
}