package com.example.nycschoolz.di

import android.content.Context
import androidx.room.Room
import com.example.nycschoolz.api.ApiInterface
import com.example.nycschoolz.api.ApiResources
import com.example.nycschoolz.repo.RepoInterface
import com.example.nycschoolz.repo.RepoRemote
import com.example.nycschoolz.room.SchoolDatabase
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class Modules {

    @Provides
    fun providesGson():Gson{ return Gson()
    }

    @Provides
    fun httpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    @Provides
    fun okHttpClient(loggingInterceptor: HttpLoggingInterceptor):OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

    @Provides
    fun retrofitBuilder(gson: Gson, okHttpClient: OkHttpClient):Retrofit =
        Retrofit.Builder()
            .baseUrl(ApiResources.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()

    @Provides
    fun getApi(retrofit: Retrofit):ApiInterface =
        retrofit.create(ApiInterface::class.java)

    @Provides
    fun remoteRepo(apiInterface: ApiInterface): RepoInterface =
        RepoRemote(apiInterface = apiInterface)

    @Provides
    fun provideSchoolDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        SchoolDatabase::class.java,
        "SchoolDatabase",
    ).build()

    @Provides
    fun provideDao(database: SchoolDatabase) = database.schoolDAO()
}