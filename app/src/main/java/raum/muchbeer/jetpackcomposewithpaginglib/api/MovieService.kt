package raum.muchbeer.jetpackcomposewithpaginglib.api

import raum.muchbeer.jetpackcomposewithpaginglib.model.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("movie/popular")
suspend fun getPopularMovies(
    @Query("api_key")apiKey: String
): MovieListResponse
}



