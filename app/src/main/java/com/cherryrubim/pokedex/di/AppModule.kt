package com.cherryrubim.pokedex.di

import android.app.Application
import androidx.room.Room
import com.cherryrubim.pokedex.core.AppConstants.BASE_URL
import com.cherryrubim.pokedex.data.local.AppDatabase
import com.cherryrubim.pokedex.data.local.converters.StatsConverter
import com.cherryrubim.pokedex.data.local.converters.TypeConverter
import com.cherryrubim.pokedex.data.remote.PokemonAPI
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providerMoshie(): Moshi {
        return Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    }

    @Provides
    @Singleton
    fun providerRoom(
        application: Application,
        typeConverter: TypeConverter,
        statsConverter: StatsConverter
    ): AppDatabase {
        return Room
            .databaseBuilder(application, AppDatabase::class.java, "Pokemon.db")
            .addTypeConverter(typeConverter)
            .addTypeConverter(statsConverter)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providerPokemonAPI(): PokemonAPI {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BASIC
                }).build()
            )
            .build()
            .create(PokemonAPI::class.java)
    }

}