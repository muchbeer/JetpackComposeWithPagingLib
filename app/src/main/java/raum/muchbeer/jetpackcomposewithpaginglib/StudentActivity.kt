package raum.muchbeer.jetpackcomposewithpaginglib

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import raum.muchbeer.jetpackcomposewithpaginglib.component.bandcomponent.TodoScreen
import raum.muchbeer.jetpackcomposewithpaginglib.ui.theme.JetpackComposeWithPagingLibTheme
import raum.muchbeer.jetpackcomposewithpaginglib.viewmodel.SchoolViewModel

@AndroidEntryPoint
class StudentActivity : ComponentActivity() {

    private val viewModel : SchoolViewModel by viewModels()

    @ExperimentalComposeUiApi
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeWithPagingLibTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    SchoolCourse(viewModel)
                }
            }
        }
    }
}

@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@Composable
fun SchoolCourse(schoolViewModel: SchoolViewModel) {
    
    TodoScreen(
        items = schoolViewModel.allSchoolState.value,
        currentlyEditing = schoolViewModel.currentEditItem,
        onAddItem = schoolViewModel::addSchool ,
        onRemoveItem = schoolViewModel::removeItem,
        onStartEdit = schoolViewModel::onEditItemSelected,
        onEditItemChange = schoolViewModel::onEditItemChange
    )
}

