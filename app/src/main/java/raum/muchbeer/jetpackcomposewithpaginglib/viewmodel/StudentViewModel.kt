package raum.muchbeer.jetpackcomposewithpaginglib.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import raum.muchbeer.jetpackcomposewithpaginglib.model.StudentModel
import raum.muchbeer.jetpackcomposewithpaginglib.repository.StudentRepository
import javax.inject.Inject

@HiltViewModel
class StudentViewModel @Inject constructor(
    val repository: StudentRepository
) : ViewModel() {

    //alternative
    val allStudentState : MutableState<List<StudentModel>> = mutableStateOf(listOf())

    val isCourseUpdate: MutableState<Boolean> = mutableStateOf(false)

    val setCourseNameUpdate : MutableState<String> = mutableStateOf("")

    val setCourseCode : MutableState<Int> = mutableStateOf(-1)

    init {
        settingStudentFlow()
    }

    private fun settingStudentFlow() = viewModelScope.launch {
        repository.retrieveLiveStudent.collect {
        //    _uiState.value
            allStudentState.value = it
        }
    }

    fun addStudent(studentCourse: StudentModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addStudentCourse(studentCourse)
        }
    }

    fun setUpdateCourse(studentModel: StudentModel) {
        isCourseUpdate.value = true
       this.setCourseNameUpdate.value = studentModel.course_name
        this.setCourseCode.value = studentModel.course_code
    }



    fun updateCourse(studentCourse: StudentModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateStudent(studentCourse = studentCourse)
        }
    }

    fun updateInputCourse(studentCourse: String, course_code : Int) = viewModelScope.launch {
        repository.updateInputCouser(studentCourse, course_code)
    }

    fun deleteCourse(studentCourse: StudentModel) {
        viewModelScope.launch (Dispatchers.IO ){
            repository.deleteStudent(studentCourse = studentCourse)
        }
    }

    fun deleteAllStudent() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllStudent()
        }
    }

    // Backing property to avoid state updates from other classes
    private val _uiState : MutableStateFlow<List<StudentModel>> = MutableStateFlow(emptyList())

    // The UI collects from this StateFlow to get its state updates
    val uiState: StateFlow<List<StudentModel>> = _uiState

}