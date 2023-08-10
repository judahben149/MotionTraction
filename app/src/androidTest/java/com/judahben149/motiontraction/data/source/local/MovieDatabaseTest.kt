package com.judahben149.motiontraction.data.source.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.judahben149.motiontraction.data.source.local.dao.CreditsDao
import com.judahben149.motiontraction.data.source.local.dao.FavoriteMovieDao
import com.judahben149.motiontraction.data.source.local.dao.MovieDao
import com.judahben149.motiontraction.data.source.local.dao.RemoteKeyDao
import com.judahben149.motiontraction.generateRandomCreditsEntity
import com.judahben149.motiontraction.generateRandomFavoriteMovieEntity
import com.judahben149.motiontraction.generateRandomMovieDetailEntity
import com.judahben149.motiontraction.generateRandomMovieEntityRemoteKey
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MovieDatabaseTest {

    private lateinit var db: MovieDatabase
    private lateinit var movieDao: MovieDao
    private lateinit var favMovieDao: FavoriteMovieDao
    private lateinit var creditsDao: CreditsDao
    private lateinit var remoteKeyDao: RemoteKeyDao

    @Before
    fun createDB() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, MovieDatabase::class.java).build()

        movieDao = db.movieDao
        favMovieDao = db.favoriteMovieDao
        creditsDao = db.creditsDao
        remoteKeyDao = db.remoteKeyDao
    }

    @After
    fun teardownDB() {
        db.close()
    }

    @Test
    fun testSaveAndGetMoviesFromMovieDao() {
        val movieDetailEntity1 = generateRandomMovieDetailEntity()
        val movieDetailEntity2 = generateRandomMovieDetailEntity()
        val movieDetailEntity3 = generateRandomMovieDetailEntity()

        movieDao.saveMovie(movieDetailEntity1)
        movieDao.saveMovie(movieDetailEntity2)
        movieDao.saveMovie(movieDetailEntity3)

        val result1 = movieDao.getMovie(movieDetailEntity1.movieId.toLong()).blockingFirst()
        val result2 = movieDao.getMovie(movieDetailEntity1.movieId.toLong()).blockingFirst()
        val result3 = movieDao.getMovie(movieDetailEntity1.movieId.toLong()).blockingFirst()


        Truth.assertThat(result1).isEqualTo(movieDetailEntity1)
        Truth.assertThat(result2).isEqualTo(movieDetailEntity1)
        Truth.assertThat(result3).isEqualTo(movieDetailEntity1)
    }

    @Test
    fun testSaveAndGetAllFavoriteMoviesFromFavoriteMovieDao() {
        val favMovieEntity1 = generateRandomFavoriteMovieEntity()
        val favMovieEntity2 = generateRandomFavoriteMovieEntity()
        val favMovieEntity3 = generateRandomFavoriteMovieEntity()

        favMovieDao.saveFavoriteMovie(favMovieEntity1)
        favMovieDao.saveFavoriteMovie(favMovieEntity2)
        favMovieDao.saveFavoriteMovie(favMovieEntity3)

        val result = favMovieDao.getAllFavoriteMovies().blockingFirst()

        Truth.assertThat(result).contains(favMovieEntity1)
        Truth.assertThat(result).contains(favMovieEntity2)
        Truth.assertThat(result).contains(favMovieEntity3)
    }

    @Test
    fun testSaveAndGetCreditsFromCreditsMovieDao() {
        val creditsEntity1 = generateRandomCreditsEntity()
        val creditsEntity2 = generateRandomCreditsEntity()
        val creditsEntity3 = generateRandomCreditsEntity()

        creditsDao.saveCredit(creditsEntity1)
        creditsDao.saveCredit(creditsEntity2)
        creditsDao.saveCredit(creditsEntity3)

        val result1 = creditsDao.getCredit(creditsEntity1.id).blockingFirst()
        val result2 = creditsDao.getCredit(creditsEntity2.id).blockingFirst()
        val result3 = creditsDao.getCredit(creditsEntity3.id).blockingFirst()

        Truth.assertThat(result1).isEqualTo(creditsEntity1)
        Truth.assertThat(result2).isEqualTo(creditsEntity2)
        Truth.assertThat(result3).isEqualTo(creditsEntity3)
    }

    @Test
    fun testSaveAndRetrieveRemoteKeys() {
        val remoteKeyEntity1 = generateRandomMovieEntityRemoteKey()
        val remoteKeyEntity2 = generateRandomMovieEntityRemoteKey()
        val remoteKeyEntity3 = generateRandomMovieEntityRemoteKey()

        remoteKeyDao.insertAllRemoteKeys(listOf(remoteKeyEntity1, remoteKeyEntity2, remoteKeyEntity3))

        val result1 = remoteKeyDao.getRemoteKeysByMovieId(remoteKeyEntity1.id)
        val result2 = remoteKeyDao.getRemoteKeysByMovieId(remoteKeyEntity2.id)
        val result3 = remoteKeyDao.getRemoteKeysByMovieId(remoteKeyEntity3.id)

        Truth.assertThat(result1).isEqualTo(remoteKeyEntity1)
        Truth.assertThat(result2).isEqualTo(remoteKeyEntity2)
        Truth.assertThat(result3).isEqualTo(remoteKeyEntity3)
    }
}