package com.judahben149.motiontraction.presentation.movieDetail

import com.judahben149.motiontraction.domain.models.DetailMovie
import com.judahben149.motiontraction.domain.models.credits.Credits

data class MovieDetailUiState(
    val movieDetail: DetailMovie = DetailMovie(),
    val credits: Credits = Credits(),
    val errorMessage: String = "",
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isGetMovieDetailSuccessful: Boolean = false
)
