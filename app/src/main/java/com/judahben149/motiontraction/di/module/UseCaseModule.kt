package com.judahben149.motiontraction.di.module

import com.judahben149.motiontraction.data.repository.MovieRepositoryImpl
import com.judahben149.motiontraction.domain.usecase.GetMovieCreditsUseCase
import com.judahben149.motiontraction.domain.usecase.GetMovieDetailsUseCase
import com.judahben149.motiontraction.domain.usecase.GetMoviePagedListUseCase
import com.judahben149.motiontraction.domain.usecase.UpdateFavoritesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun providesGetMovieDetailsUseCase(repository: MovieRepositoryImpl): GetMovieDetailsUseCase {
        return GetMovieDetailsUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesGetMovieCreditsUseCase(repository: MovieRepositoryImpl): GetMovieCreditsUseCase {
        return GetMovieCreditsUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesGetMoviePagedListUseCase(repository: MovieRepositoryImpl): GetMoviePagedListUseCase {
        return GetMoviePagedListUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesUpdateFavoritesUseCase(repository: MovieRepositoryImpl): UpdateFavoritesUseCase {
        return UpdateFavoritesUseCase(repository)
    }
}