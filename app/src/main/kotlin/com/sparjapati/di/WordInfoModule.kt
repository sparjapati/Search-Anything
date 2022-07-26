package com.sparjapati.di

import android.app.Application
import androidx.room.Room
import com.google.gson.Gson
import com.sparjapati.searchAnything.data.local.WordInfoDao
import com.sparjapati.searchAnything.data.local.WordInfoDatabase
import com.sparjapati.searchAnything.data.remote.DictionaryApi
import com.sparjapati.searchAnything.data.repository.WordInfoRepositoryImpl
import com.sparjapati.searchAnything.data.utils.GsonParser
import com.sparjapati.searchAnything.domain.repository.WordInfoRepository
import com.sparjapati.searchAnything.domain.useCases.GetWordInfo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WordInfoModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @Provides
    @Singleton
    fun provideDictionaryApi(client: OkHttpClient): DictionaryApi {
        return Retrofit.Builder()
            .baseUrl(DictionaryApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideWordInfoDatabase(app: Application): WordInfoDatabase = Room.databaseBuilder(
        app,
        WordInfoDatabase::class.java,
        "word_info_database.db"
    ).addTypeConverter(GsonParser(Gson()))
        .build()

    @Provides
    @Singleton
    fun provideWordsInfoDao(database: WordInfoDatabase) :WordInfoDao = database.dao

    @Provides
    @Singleton
    fun provideWordInfoRepository(api: DictionaryApi, dao: WordInfoDao): WordInfoRepository {
        return WordInfoRepositoryImpl(api, dao)
    }

    @Provides
    @Singleton
    fun provideWordInfoUseCase(wordInfoRepository: WordInfoRepository): GetWordInfo = GetWordInfo(wordInfoRepository)

}