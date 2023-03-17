package com.example.a20230317_yovanyortiz_nycschools.di

import com.example.a20230317_yovanyortiz_nycschools.data.remote.NYCApi
import com.example.a20230317_yovanyortiz_nycschools.data.repository.NYCSchoolsRepository
import com.example.a20230317_yovanyortiz_nycschools.data.repository.NYCSchoolsRepositoryImpl
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    fun provideGson(): Gson = Gson()

    @Provides
    fun provideHttpInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

    @Provides
    fun provideRetrofit(
        gson: Gson,
        okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(NYCApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()

    @Provides
    fun provideNYCApi(
        retrofit: Retrofit
    ): NYCApi = retrofit.create(NYCApi::class.java)

    @Provides
    fun provideRepository(
        nycApi: NYCApi
    ): NYCSchoolsRepository = NYCSchoolsRepositoryImpl(nycApi)

    @Provides
    fun providesIoDispatcher() : CoroutineDispatcher = Dispatchers.IO
}