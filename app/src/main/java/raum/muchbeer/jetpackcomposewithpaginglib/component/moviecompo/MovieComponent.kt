package raum.muchbeer.jetpackcomposewithpaginglib.component.moviecompo

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import kotlinx.coroutines.flow.Flow
import raum.muchbeer.jetpackcomposewithpaginglib.BuildConfig
import raum.muchbeer.jetpackcomposewithpaginglib.model.Movie
import raum.muchbeer.jetpackcomposewithpaginglib.state.ErrorItem
import raum.muchbeer.jetpackcomposewithpaginglib.state.LoadingItem
import raum.muchbeer.jetpackcomposewithpaginglib.state.LoadingView
import raum.muchbeer.jetpackcomposewithpaginglib.viewmodel.MovieViewModel

@Composable
fun MainScreen(movieViewModel: MovieViewModel) {

  //  val movieViewModel = hiltViewModel<MovieViewModel>()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Muchbeer Movies") }
            )
        },
        content = {
            MovieList(movies = movieViewModel.moviesNew)
        }
    )
}


@Composable
fun MovieList(movies: Flow<PagingData<Movie>>) {
    val lazyMovieItems = movies.collectAsLazyPagingItems()

    LazyColumn {

        items(lazyMovieItems) { movie ->
            MovieItem(movie = movie!!)
        }

        lazyMovieItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { LoadingView(modifier = Modifier.fillParentMaxSize()) }
                }
                loadState.append is LoadState.Loading -> {
                    item { LoadingItem() }
                }
                loadState.refresh is LoadState.Error -> {
                    val e = lazyMovieItems.loadState.refresh as LoadState.Error
                    item {
                        ErrorItem(
                            message = e.error.localizedMessage!!,
                            modifier = Modifier.fillParentMaxSize(),
                            onClickRetry = { retry() }
                        )
                    }
                }
                loadState.append is LoadState.Error -> {
                    val e = lazyMovieItems.loadState.append as LoadState.Error
                    item {
                        ErrorItem(
                            message = e.error.localizedMessage!!,
                            onClickRetry = { retry() }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MovieItem(movie: Movie) {

    Card(modifier = Modifier.fillMaxWidth()
        .background(color = Color.LightGray)
        .padding(8.dp),
       // .border(width = 1.dp, color = Color.LightGray)
    //    .clip(MaterialTheme.shapes.medium),
        elevation = 15.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable {

                }
            ,
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            MovieImage(
                BuildConfig.LARGE_IMAGE_URL + movie.poster_path )
            MovieTitle(movie)
        }
    }

}

@Composable
fun MovieImage(
    poster_path: String,
) {
//"${loadPicture(imageUrl = poster_path, imageDefault = DEFAULT_IMAGE).value}"

    Image(painter = rememberImagePainter(
        data = poster_path,
        builder = {
            transformations(CircleCropTransformation())
        }
    ), contentDescription = "",
    modifier = Modifier
        .size(100.dp)
        .padding(16.dp),
    contentScale = ContentScale.Crop)

}

@Composable
fun MovieTitle(movie: Movie) {
    Log.d("Movies", "THe titles are : ${movie.title}")
    Column(modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth()) {

        Text(
            text = movie.title,
            maxLines = 1,
            style = MaterialTheme.typography.h6,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(start = 16.dp)
        )
        Text(text = movie.overview,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(start = 16.dp)
                .fillMaxWidth())
    }

}


