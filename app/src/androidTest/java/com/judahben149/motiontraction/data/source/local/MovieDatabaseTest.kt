package com.judahben149.motiontraction.data.source.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.judahben149.motiontraction.data.source.local.dao.MovieDao
import com.judahben149.motiontraction.generateRandomMovieDetailEntity
import io.reactivex.schedulers.TestScheduler
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class MovieDatabaseTest {

    private lateinit var db: MovieDatabase
    private lateinit var movieDao: MovieDao

    private val testScheduler = TestScheduler()

    @Before
    fun createDB() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, MovieDatabase::class.java).build()

        movieDao = db.movieDao
    }

    @After
    fun teardownDB() {
        db.close()
    }

    @Test
    fun testInsertAndRetrieveMovie() {
        val movieDetailEntity = generateRandomMovieDetailEntity()

        movieDao.saveMovie(movieDetailEntity)
        testScheduler.advanceTimeBy(200, TimeUnit.MILLISECONDS)

        movieDao.getMovie(movieDetailEntity.movieId.toLong())
            .subscribeOn(testScheduler)
            .test()
            .assertValue { movie ->
                movie == movieDetailEntity
            }

    }
}