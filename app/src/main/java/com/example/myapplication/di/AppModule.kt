package com.example.myapplication.di

import android.content.Context
import android.content.pm.PackageInfo
import com.example.myapplication.base.BaseApplication
import com.example.myapplication.data.shared.DataManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.Contexts
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private val loggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)


    @Singleton
    @Provides
    fun providePackageInfo(@ApplicationContext context: Context): PackageInfo =
        context.packageManager.getPackageInfo(context.packageName, 0);


    @Singleton
    @Provides
    @Named("VersionName")
    fun provideVersionName(packageInfo: PackageInfo): String =
        packageInfo.versionName

    @Singleton
    @Provides
    fun provideDataManager(@ApplicationContext context: Context): DataManager =
        (Contexts.getApplication(context) as BaseApplication).dataManager!!


    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.MINUTES)
            .retryOnConnectionFailure(true)
            .writeTimeout(5, TimeUnit.MINUTES)
            .readTimeout(5, TimeUnit.MINUTES)
            .addInterceptor(loggingInterceptor)
            .build()


    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl("http://demo.wakeb.tech/visitor-lpr/public/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

}