package raum.muchbeer.jetpackcomposewithpaginglib.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import raum.muchbeer.jetpackcomposewithpaginglib.model.Movie
import raum.muchbeer.jetpackcomposewithpaginglib.repository.MovieRepository
import raum.muchbeer.jetpackcomposewithpaginglib.repository.pagedcompose.MovieSource
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel(){

    val movies : Flow<PagingData<Movie>> = Pager(
        PagingConfig(pageSize = 20)) {
            MovieSource(repository)
    }.flow

    val moviesNew : Flow<PagingData<Movie>> = Pager(
        config = PagingConfig(
            pageSize = 10,
            maxSize = 120,
            enablePlaceholders = false
        )) {
        MovieSource(repository)

    }.flow


  /*  val moviesNew : Flow<PagingData<Movie>> =  config = PagingConfig(
        pageSize = 20,
        maxSize = 100,
        enablePlaceholders = false
    ),
    pagingSourceFactory = { UnsplashedPagingSource(unsplashApi, search) }*/
}