package com.judahben149.motiontraction.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.judahben149.motiontraction.data.remote.MovieService
import com.judahben149.motiontraction.data.remote.dto.movieList.DiscoverMoviesListData
import com.judahben149.motiontraction.utils.Constants.STARTING_PAGE_INDEX
import com.judahben149.motiontraction.utils.logThis
import okio.IOException
import retrofit2.HttpException

class DiscoverMoviesPagingDataSource(private val service: MovieService): PagingSource<Int, DiscoverMoviesListData>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DiscoverMoviesListData> {
        return try {
            val currentPage = params.key ?: STARTING_PAGE_INDEX
            val response = service.fetchDiscoverMoviesList(currentPage)
            val movies = response.body()?.data!!

            val movieResponse = mutableListOf<DiscoverMoviesListData>()
            movieResponse.addAll(movies)

            "Loaded ${movies.size} items".logThis(tag = "MyPagingSource")

            LoadResult.Page(
                data = movieResponse,
                prevKey = if (currentPage == STARTING_PAGE_INDEX) null else currentPage.minus(1),
                nextKey = currentPage.plus(1)
            )

        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, DiscoverMoviesListData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}