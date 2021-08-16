package raum.muchbeer.jetpackcomposewithpaginglib.repository

import raum.muchbeer.jetpackcomposewithpaginglib.BuildConfig
import raum.muchbeer.jetpackcomposewithpaginglib.api.MovieService
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieService: MovieService
) {
    suspend fun getPopularMovies() =
        movieService.getPopularMovies(BuildConfig.API_KEY)
}