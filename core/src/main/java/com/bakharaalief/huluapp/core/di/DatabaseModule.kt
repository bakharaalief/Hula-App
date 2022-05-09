package com.bakharaalief.huluapp.core.di

import android.content.Context
import androidx.room.Room
import com.bakharaalief.huluapp.core.data.source.local.room.MovieDB
import com.bakharaalief.huluapp.core.data.source.local.room.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    private val passphrase: ByteArray = SQLiteDatabase.getBytes("bakharaalief".toCharArray())
    private val factory = SupportFactory(passphrase)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MovieDB = Room.databaseBuilder(
        context,
        MovieDB::class.java, "Movies.db"
    ).fallbackToDestructiveMigration()
        .openHelperFactory(factory)
        .build()

    @Provides
    fun provideMovieDao(database: MovieDB): MovieDao = database.movieDao()
}