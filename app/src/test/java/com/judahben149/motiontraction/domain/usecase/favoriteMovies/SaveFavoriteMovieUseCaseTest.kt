package com.judahben149.motiontraction.domain.usecase.favoriteMovies

import com.judahben149.motiontraction.domain.repository.MovieRepository
import com.judahben149.motiontraction.generateRandomFavoriteMovieEntity
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class SaveFavoriteMovieUseCaseTest {

    @Mock
    private lateinit var repository: MovieRepository
    private lateinit var useCase: SaveFavoriteMovieUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = SaveFavoriteMovieUseCase(repository)
    }

    @Test
    fun `saveFavoriteMovie invokes repository's saveFavoriteMovie`() {
        val favoriteMovieEntity = generateRandomFavoriteMovieEntity()

        useCase.saveFavoriteMovie(favoriteMovieEntity)
        Mockito.verify(repository).saveFavoriteMovie(favoriteMovieEntity)
    }
}