package raum.muchbeer.jetpackcomposewithpaginglib.component.homepage

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import raum.muchbeer.jetpackcomposewithpaginglib.component.NavGraph
import raum.muchbeer.jetpackcomposewithpaginglib.model.StudentModel
import raum.muchbeer.jetpackcomposewithpaginglib.viewmodel.StudentViewModel

@ExperimentalMaterialApi
@Composable
fun HomeView(navController: NavController) {
    val context = LocalContext.current
    val mStudentViewModel = hiltViewModel<StudentViewModel>()

/*
    val mBackUpViewModel: OnDemandBackupViewModel = viewModel(
        factory = OnDemandBackupViewModelFactory(context.applicationContext as Application)
    )
*/

    val items = mStudentViewModel.allStudentState.value


    Column(
        modifier = Modifier.padding(16.dp).fillMaxWidth()
    ) {
        Text("Student Course", fontSize = 25.sp, fontStyle = FontStyle.Italic,
        modifier = Modifier.align(Alignment.CenterHorizontally) )
        Spacer(modifier = Modifier.padding(bottom = 16.dp))
        CustomCardState(navController, mStudentViewModel)
        StudentList(studentList = items, mStudentVM = mStudentViewModel, navController = navController)
        Spacer(modifier = Modifier.padding(top = 32.dp))
        //BackUpButton(mBackUpViewModel)
    }
}

@Composable
fun CustomCardState(
    navController: NavController,
    mStudentVM: StudentViewModel
) {
    Column(
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Button(onClick = { navController.navigate(NavGraph.Destinations.AddStudent) }) {
                Text(text = "Ådd Student")
            }
            Button(onClick = { mStudentVM.deleteAllStudent() }) {
                Text(text = "Clear all")
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun StudentList(
    studentList: List<StudentModel>,
    mStudentVM: StudentViewModel,
    navController: NavController

    ) {
    val context = LocalContext.current
    Log.d("AddView", "ViewStuff")

    LazyColumn() {
       items(items = studentList, itemContent = { studentItem->
           val name = rememberSaveable { mutableStateOf(studentItem.isDone) }

           ListItem(
               text = { Text(text = studentItem.course_name, maxLines = 1,
               overflow = TextOverflow.Ellipsis,
                   modifier = Modifier.fillMaxWidth()
                       .clickable {
                    mStudentVM.setUpdateCourse(studentItem)
                     navController.navigate(NavGraph.Destinations.AddStudent)

                       })},
               icon = {
                   IconButton(onClick = {
                       mStudentVM.deleteCourse(studentItem)
                   }) {
                       Icon(
                           Icons.Default.Delete,
                           contentDescription = null
                       )
                   }
               },
               trailing = {
                   Checkbox(
                       checked = name.value,
                       onCheckedChange = {
                        //   mStudentVM.onCheckboxChange(it)
                      //  val studentUpdate =  StudentModel(studentItem.course_name, mStudentVM.isDone.value)
                     //      mStudentVM.updateCourse(studentCourse = studentUpdate)
                           name.value = it
                            studentItem.isDone = name.value
                           mStudentVM.updateCourse(studentCourse = studentItem)
                           Toast.makeText(context, "Updated the checked is : ${studentItem.isDone}!", Toast.LENGTH_SHORT).show()
                       },
                   )
               }
           )
           Divider()
       })
    }
}


