package com.judahben149.motiontraction.di.module

import com.judahben149.motiontraction.data.repository.MovieRepositoryImpl
import com.judahben149.motiontraction.domain.usecase.favoriteMovies.GetFavoriteMoviesUseCase
import com.judahben149.motiontraction.domain.usecase.credits.GetMovieCreditsUseCase
import com.judahben149.motiontraction.domain.usecase.favoriteMovies.SaveFavoriteMovieUseCase
import com.judahben149.motiontraction.domain.usecase.movieDetail.GetMovieDetailsUseCase
import com.judahben149.motiontraction.domain.usecase.movieList.GetMoviePagedListUseCase
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
    fun providesGetFavoriteMoviesUseCase(repository: MovieRepositoryImpl): GetFavoriteMoviesUseCase {
        return GetFavoriteMoviesUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesSaveFavoriteMovieUseCase(repository: MovieRepositoryImpl): SaveFavoriteMovieUseCase {
        return SaveFavoriteMovieUseCase(repository)
    }
}