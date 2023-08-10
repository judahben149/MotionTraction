package com.judahben149.motiontraction

import com.judahben149.motiontraction.data.source.local.entity.credits.CastEntity
import com.judahben149.motiontraction.data.source.local.entity.credits.CreditsEntity
import com.judahben149.motiontraction.data.source.local.entity.credits.CrewEntity
import com.judahben149.motiontraction.data.source.local.entity.favoriteMovies.FavoriteMovieEntity
import com.judahben149.motiontraction.data.source.local.entity.movieDetail.GenreEntity
import com.judahben149.motiontraction.data.source.local.entity.movieDetail.MovieDetailEntity
import com.judahben149.motiontraction.data.source.local.entity.movieDetail.ProductionCompanyEntity
import com.judahben149.motiontraction.data.source.local.entity.movieDetail.ProductionCountryEntity
import com.judahben149.motiontraction.data.source.local.entity.movieDetail.SpokenLanguageEntity
import com.judahben149.motiontraction.data.source.local.entity.movieList.MovieEntity
import com.judahben149.motiontraction.data.source.local.entity.movieList.MovieEntityRemoteKey
import com.judahben149.motiontraction.data.source.local.entity.movieList.MovieResponseEntity
import kotlin.random.Random

fun generateRandomCreditsEntity(): CreditsEntity {
    val id = Random.nextInt()
    val castEntity = generateRandomCastEntityList()
    val crewEntity = generateRandomCrewEntityList()
    return CreditsEntity(id, castEntity, crewEntity)
}

fun generateRandomCastEntityList(): List<
        CastEntity> {
    val castList = mutableListOf<CastEntity>()
    repeat(Random.nextInt(1, 10)) {
        castList.add(generateRandomCastEntity())
    }
    return castList
}

fun generateRandomCastEntity(): CastEntity {
    return CastEntity(
        adult = Random.nextBoolean(),
        castId = Random.nextInt(),
        character = "Character-${Random.nextInt()}",
        creditId = "Credit-${Random.nextInt()}",
        gender = Random.nextInt(0, 3),
        id = Random.nextInt(),
        knownForDepartment = "Department-${Random.nextInt()}",
        name = "Name-${Random.nextInt()}",
        order = Random.nextInt(),
        originalName = "OriginalName-${Random.nextInt()}",
        popularity = Random.nextDouble(),
        profilePath = "ProfilePath-${Random.nextInt()}"
    )
}

fun generateRandomCrewEntityList(): List<CrewEntity> {
    val crewList = mutableListOf<CrewEntity>()
    repeat(Random.nextInt(1, 10)) {
        crewList.add(generateRandomCrewEntity())
    }
    return crewList
}

fun generateRandomCrewEntity(): CrewEntity {
    return CrewEntity(
        adult = Random.nextBoolean(),
        creditId = "Credit-${Random.nextInt()}",
        department = "Department-${Random.nextInt()}",
        gender = Random.nextInt(0, 3),
        id = Random.nextInt(),
        job = "Job-${Random.nextInt()}",
        knownForDepartment = "KnownDepartment-${Random.nextInt()}",
        name = "Name-${Random.nextInt()}",
        originalName = "OriginalName-${Random.nextInt()}",
        popularity = Random.nextDouble(),
        profilePath = "ProfilePath-${Random.nextInt()}"
    )
}

// Favorite Movie Generators
fun generateRandomFavoriteMovieEntity(): FavoriteMovieEntity {
    return FavoriteMovieEntity(
        movieId = Random.nextInt(),
        adult = Random.nextBoolean(),
        backdropPath = "BackdropPath-${Random.nextInt()}",
        originalTitle = "OriginalTitle-${Random.nextInt()}",
        popularity = Random.nextDouble(),
        posterPath = "PosterPath-${Random.nextInt()}",
        releaseDate = "ReleaseDate-${Random.nextInt()}",
        runtime = Random.nextInt(60, 240), // Assuming a movie runtime between 1 and 4 hours
        tagline = "Tagline-${Random.nextInt()}",
        title = "Title-${Random.nextInt()}",
        voteAverage = Random.nextDouble(1.0, 10.0),
        voteCount = Random.nextInt(0, 10000)
    )
}

fun generateRandomFavoriteMovieEntityList(size: Int): List<FavoriteMovieEntity> {
    val movieList = mutableListOf<FavoriteMovieEntity>()
    repeat(size) {
        movieList.add(generateRandomFavoriteMovieEntity())
    }
    return movieList
}

//Movie Detail Generators

fun generateRandomMovieDetailEntity(): MovieDetailEntity {
    return MovieDetailEntity(
        movieId = Random.nextInt(),
        adult = Random.nextBoolean(),
        backdropPath = "BackdropPath-${Random.nextInt()}",
        budget = Random.nextDouble(1000000.0, 100000000.0),
        genres = generateRandomGenreEntityList(),
        homepageUrl = "HomepageUrl-${Random.nextInt()}",
        imdbId = "IMDb-${Random.nextInt()}",
        originalLanguage = "Language-${Random.nextInt()}",
        originalTitle = "OriginalTitle-${Random.nextInt()}",
        overview = "Overview-${Random.nextInt()}",
        popularity = Random.nextDouble(),
        posterPath = "PosterPath-${Random.nextInt()}",
        productionCompanies = generateRandomProductionCompanyEntityList(),
        productionCountries = generateRandomProductionCountryEntityList(),
        releaseDate = "ReleaseDate-${Random.nextInt()}",
        revenue = Random.nextDouble(1000000.0, 1000000000.0),
        runtime = Random.nextInt(60, 240), // Assuming a movie runtime between 1 and 4 hours
        status = "Status-${Random.nextInt()}",
        spokenLanguages = generateRandomSpokenLanguageEntityList(),
        tagline = "Tagline-${Random.nextInt()}",
        title = "Title-${Random.nextInt()}",
        video = Random.nextBoolean(),
        voteAverage = Random.nextDouble(1.0, 10.0),
        voteCount = Random.nextInt(0, 10000),
        isFavorite = Random.nextBoolean()
    )
}

fun generateRandomProductionCompanyEntity(): ProductionCompanyEntity {
    return ProductionCompanyEntity(
        id = Random.nextInt(),
        logoPath = "LogoPath-${Random.nextInt()}",
        name = "ProductionCompany-${Random.nextInt()}",
        originCountry = "Country-${Random.nextInt()}"
    )
}

fun generateRandomProductionCompanyEntityList(): List<ProductionCompanyEntity> {
    val companyList = mutableListOf<ProductionCompanyEntity>()
    repeat(Random.nextInt(1, 5)) {
        companyList.add(generateRandomProductionCompanyEntity())
    }
    return companyList
}

fun generateRandomProductionCountryEntity(): ProductionCountryEntity {
    return ProductionCountryEntity(
        iso6391 = "ISO-${Random.nextInt()}",
        name = "Country-${Random.nextInt()}"
    )
}

fun generateRandomProductionCountryEntityList(): List<ProductionCountryEntity> {
    val countryList = mutableListOf<ProductionCountryEntity>()
    repeat(Random.nextInt(1, 4)) {
        countryList.add(generateRandomProductionCountryEntity())
    }
    return countryList
}

fun generateRandomSpokenLanguageEntity(): SpokenLanguageEntity {
    return SpokenLanguageEntity(
        englishName = "English-${Random.nextInt()}",
        iso6391 = "ISO-${Random.nextInt()}",
        name = "Language-${Random.nextInt()}"
    )
}

fun generateRandomSpokenLanguageEntityList(): List<SpokenLanguageEntity> {
    val languageList = mutableListOf<SpokenLanguageEntity>()
    repeat(Random.nextInt(1, 4)) {
        languageList.add(generateRandomSpokenLanguageEntity())
    }
    return languageList
}

fun generateRandomGenreEntity(): GenreEntity {
    return GenreEntity(
        id = Random.nextInt(),
        name = "Genre-${Random.nextInt()}"
    )
}

fun generateRandomGenreEntityList(): List<GenreEntity> {
    val genreList = mutableListOf<GenreEntity>()
    repeat(Random.nextInt(1, 4)) {
        genreList.add(generateRandomGenreEntity())
    }
    return genreList
}

// Movie List Generators

fun generateRandomMovieEntity(): MovieEntity {
    return MovieEntity(
        movieId = Random.nextLong(),
        adult = Random.nextBoolean(),
        backdropPath = "BackdropPath-${Random.nextInt()}",
        originalLanguage = "Language-${Random.nextInt()}",
        originalTitle = "OriginalTitle-${Random.nextInt()}",
        popularity = Random.nextDouble(),
        posterPath = "PosterPath-${Random.nextInt()}",
        releaseDate = "ReleaseDate-${Random.nextInt()}",
        title = "Title-${Random.nextInt()}",
        video = Random.nextBoolean(),
        voteAverage = Random.nextDouble(1.0, 10.0),
        voteCount = Random.nextInt(0, 10000),
        isFavorite = Random.nextBoolean()
    )
}

fun generateRandomMovieResponseEntity(page: Int, totalPages: Int): MovieResponseEntity {
    val movieList = mutableListOf<MovieEntity>()
    repeat(Random.nextInt(1, 20)) {
        movieList.add(generateRandomMovieEntity())
    }
    return MovieResponseEntity(page, movieList, totalPages)
}

fun generateRandomMovieEntityRemoteKey(): MovieEntityRemoteKey {
    return MovieEntityRemoteKey(
        id = Random.nextLong(),
        prev = Random.nextInt(),
        next = Random.nextInt()
    )
}

