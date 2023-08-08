package com.judahben149.motiontraction.di.module

import com.judahben149.motiontraction.data.repository.MovieRepositoryImpl
import com.judahben149.motiontraction.data.source.local.MovieDatabase
import com.judahben149.motiontraction.data.source.remote.MovieService
import com.judahben149.motiontraction.utils.NetworkUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesMovieRepositoryImpl(moviesService: MovieService, database: MovieDatabase, networkUtils: NetworkUtils): MovieRepositoryImpl {
        return MovieRepositoryImpl(moviesService, database, networkUtils)
    }
}