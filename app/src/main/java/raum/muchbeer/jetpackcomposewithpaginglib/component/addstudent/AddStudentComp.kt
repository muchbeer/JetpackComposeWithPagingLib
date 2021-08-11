package raum.muchbeer.jetpackcomposewithpaginglib.component.addstudent

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import raum.muchbeer.jetpackcomposewithpaginglib.component.NavGraph
import raum.muchbeer.jetpackcomposewithpaginglib.model.StudentModel
import raum.muchbeer.jetpackcomposewithpaginglib.viewmodel.StudentViewModel

@Composable
fun AddView(navController: NavController) {
    val inputViewModel = InputViewModel()
    val context = LocalContext.current
    val mStudentViewModel = hiltViewModel<StudentViewModel>(
        navController.getBackStackEntry(NavGraph.Destinations.Home)

    )


    Scaffold(
        floatingActionButton = {
            ExtendedFAB {
                insertStudentInDB(inputViewModel._course_state.value, mStudentViewModel)
                Log.d("AddView", "ViewStuff")
                Toast.makeText(context, "Added Student", Toast.LENGTH_SHORT).show()
                navController.navigate(NavGraph.Destinations.Home)
            }
        }
    ) {
        InputFieldState(inputViewModel)
    }
}

    @Composable
    fun InputFieldState(inputViewModel: InputViewModel) {
        val course_name: String = inputViewModel._course_state.value

        Column(modifier = Modifier.padding(16.dp)) {
            InputField(course_name) { inputViewModel.onInputChange(it) }
            Spacer(modifier = Modifier.padding(10.dp))
        }
    }

    @Composable
    fun InputField(
        name: String,
        onValChange: ((String) -> Unit)?
    ) {
        val focusManager = LocalFocusManager.current

        if (onValChange != null) {
            OutlinedTextField(
                value = name,
                placeholder = { Text(text = "Enter course") },
                modifier = Modifier
                    .padding(all = 16.dp)
                    .fillMaxWidth(),
                onValueChange = onValChange,
                singleLine = true,
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            )
        }
    }

    @Composable
    fun ExtendedFAB(onClick: () -> Unit) {
        ExtendedFloatingActionButton(
            text = { Text("Save Course") },
            onClick = onClick,
            elevation = FloatingActionButtonDefaults.elevation(8.dp)
        )
    }

    fun insertStudentInDB(course_name: String, mStudentViewModel: StudentViewModel) {
        if (course_name.isNotEmpty()) {
            val studentModel = StudentModel(
                course_name = course_name,
                isDone = false
            )
            mStudentViewModel.addStudent(studentModel)
        }
    }

    class InputViewModel : ViewModel() {
        private val _course: MutableLiveData<String> = MutableLiveData("")
        val _course_state : MutableState<String> = mutableStateOf("")

        fun onInputChange(newName: String) {
            _course_state.value = newName
        }
    }
