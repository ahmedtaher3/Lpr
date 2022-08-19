package com.example.myapplication.di


import android.content.Context
import com.example.myapplication.data.retrofit.ApiServices
import com.example.myapplication.data.shared.DataManager
import com.example.myapplication.ui.auth.AuthRepository
import com.example.myapplication.ui.main.fragments.history.HistoryRepository
import com.example.myapplication.ui.main.fragments.setting.SettingRepository
import com.example.myapplication.ui.main.newVisitor.ActionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object Repository {


    @Singleton
    @Provides
    fun provideCapRepository(
        @ApplicationContext appContext: Context,
        dataManager: DataManager,
        api: ApiServices
    ): AuthRepository =
        AuthRepository(appContext, dataManager, api)

    @Singleton
    @Provides
    fun provideActionRepository(
        @ApplicationContext appContext: Context,
        dataManager: DataManager,
        api: ApiServices
    ): ActionRepository =
        ActionRepository(appContext, dataManager, api)


    @Singleton
    @Provides
    fun provideHistoryRepository(
        @ApplicationContext appContext: Context,
        dataManager: DataManager,
        api: ApiServices
    ): HistoryRepository =
        HistoryRepository(appContext, dataManager, api)




    @Singleton
    @Provides
    fun provideSettingRepository(
        @ApplicationContext appContext: Context,
        dataManager: DataManager,
        api: ApiServices
    ): SettingRepository =
        SettingRepository(appContext, dataManager, api)
}