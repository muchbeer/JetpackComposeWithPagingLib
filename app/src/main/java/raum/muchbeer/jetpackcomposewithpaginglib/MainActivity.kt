package raum.muchbeer.jetpackcomposewithpaginglib

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import raum.muchbeer.jetpackcomposewithpaginglib.component.NavigationComponent
import raum.muchbeer.jetpackcomposewithpaginglib.ui.theme.JetpackComposeWithPagingLibTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationComponent()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Log.d("AddView", "ViewStuff")

}