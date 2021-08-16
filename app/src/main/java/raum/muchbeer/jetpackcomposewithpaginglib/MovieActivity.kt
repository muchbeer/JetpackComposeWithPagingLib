package raum.muchbeer.jetpackcomposewithpaginglib

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import raum.muchbeer.jetpackcomposewithpaginglib.component.moviecompo.MainScreen
import raum.muchbeer.jetpackcomposewithpaginglib.viewmodel.MovieViewModel

@AndroidEntryPoint
class MovieActivity : AppCompatActivity() {

  private val viewModel : MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen(viewModel)

        }
    }
}