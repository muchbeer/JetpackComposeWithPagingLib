package raum.muchbeer.jetpackcomposewithpaginglib.repository

import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import raum.muchbeer.jetpackcomposewithpaginglib.database.StudentDao
import raum.muchbeer.jetpackcomposewithpaginglib.model.StudentModel
import javax.inject.Inject

class StudentRepository @Inject constructor(
    val studentDao : StudentDao
) {

    val retrieveLiveStudent : Flow<List<StudentModel>> = studentDao.getAllStudent()

    suspend fun addStudentCourse(studentCourse: StudentModel) {
        Log.d("AddView", "ViewStuff")
        studentDao.insert(studentCourse)
    }

    suspend fun updateStudent(studentCourse: StudentModel) {
        studentDao.update(studentCourse)
    }

    suspend fun updateInputCouser(studentCourse: String, course_code : Int) {
        studentDao.updateCourse(studentCourse, course_code)
    }
    suspend fun deleteStudent(studentCourse: StudentModel) {
        studentDao.delete(studentCourse)
    }

    suspend fun deleteAllStudent() {
        studentDao.deleteAllStudent()
    }
}