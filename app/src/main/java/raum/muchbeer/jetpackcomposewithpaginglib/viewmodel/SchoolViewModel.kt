package raum.muchbeer.jetpackcomposewithpaginglib.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import raum.muchbeer.jetpackcomposewithpaginglib.model.SchoolModel
import raum.muchbeer.jetpackcomposewithpaginglib.repository.SchoolRepository
import javax.inject.Inject

@HiltViewModel
class SchoolViewModel @Inject constructor(val repository: SchoolRepository) : ViewModel(){

    val allSchoolState : MutableState<List<SchoolModel>> = mutableStateOf(listOf())

    val setEditSchoolPosition : MutableState<Int> = mutableStateOf(-1)


    private val currentEditPosition = mutableStateOf(-1)

    init {
        settingSchoolFlow()
    }

    private fun settingSchoolFlow() = viewModelScope.launch {
        repository.retrieveLiveSchool.collect {
            //    _uiState.value
            allSchoolState.value = it
        }
    }

    val currentEditItem: SchoolModel?
        get() = allSchoolState.value.getOrNull(setEditSchoolPosition.value)


    fun addSchool(studentCourse: SchoolModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addSchool(studentCourse)
        }
    }

    fun onEditItemSelected(item: SchoolModel) {
        this.setEditSchoolPosition.value = allSchoolState.value.indexOf(item)    }

    fun onEditItemChange(studentCourse: SchoolModel) = viewModelScope.launch {

        repository.updateInputSchool(studentCourse.schoolname,
            studentCourse.id.toInt())
    }

    fun removeItem(studentCourse: SchoolModel) {
        viewModelScope.launch (Dispatchers.IO ){
            repository.deleteSchool(studentCourse = studentCourse)
           // onEditDone()
        }
    }


    var todoItems = mutableStateListOf<SchoolModel>()
        private set

    val currentEditItem2: SchoolModel?
        get() = todoItems.getOrNull(currentEditPosition.value)

    fun addItem(item: SchoolModel) {
        todoItems.add(item)
    }

    fun removeItem2(item: SchoolModel) {
        todoItems.remove(item)
        onEditDone2() // don't keep the editor open when removing items
    }

    fun onEditItemSelectedOld(item: SchoolModel) {
        this.currentEditPosition.value = todoItems.indexOf(item)
    }

    fun onEditDone2() {
        currentEditPosition.value = -1
    }

    fun onEditItemChange2(item: SchoolModel) {
        val currentItem = requireNotNull(currentEditItem)
        require(currentItem.id == item.id) {
            "You can only change an item with the same id as currentEditItem"
        }

        todoItems[currentEditPosition.value] = item
    }
}