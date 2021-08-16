package raum.muchbeer.jetpackcomposewithpaginglib.repository.pagedcompose

import androidx.paging.PagingSource
import androidx.paging.PagingState
import raum.muchbeer.jetpackcomposewithpaginglib.model.Movie
import raum.muchbeer.jetpackcomposewithpaginglib.repository.MovieRepository
import raum.muchbeer.jetpackcomposewithpaginglib.repository.StudentRepository
import retrofit2.HttpException
import java.io.IOException

private const val MOVIE_STARTING_PAGE_INDEX = 1
class MovieSource(
    private val repository: MovieRepository
) : PagingSource<Int, Movie>(){
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {

        val position = params.key ?: MOVIE_STARTING_PAGE_INDEX

        return try {
            val nextPage = params.key ?: 1
            val movieListResponse = repository.getPopularMovies()

            LoadResult.Page(
                data = movieListResponse.results,
                prevKey = if (position == MOVIE_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (movieListResponse.results.isEmpty()) null else position + 1
            )
        }  catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (http: HttpException) {
            LoadResult.Error(http)
        }
    }
}