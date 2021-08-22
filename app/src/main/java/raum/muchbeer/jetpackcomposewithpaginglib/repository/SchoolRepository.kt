package raum.muchbeer.jetpackcomposewithpaginglib.repository

import android.util.Log
import kotlinx.coroutines.flow.Flow
import raum.muchbeer.jetpackcomposewithpaginglib.database.SchoolDao
import raum.muchbeer.jetpackcomposewithpaginglib.model.SchoolModel
import raum.muchbeer.jetpackcomposewithpaginglib.model.StudentModel
import javax.inject.Inject

class SchoolRepository @Inject constructor(
    val schoolDao: SchoolDao
) {

    val retrieveLiveSchool : Flow<List<SchoolModel>> = schoolDao.getAllSchool()

    suspend fun addSchool(schoolCourse: SchoolModel) {
        schoolDao.insert(schoolCourse)
    }

    suspend fun updateStudent(schoolCourse: SchoolModel) {
        schoolDao.update(schoolCourse)
    }

    suspend fun updateInputSchool(studentCourse: String, course_id: Int) {
        schoolDao.updateSchool(studentCourse, course_id)
    }

    suspend fun deleteSchool(studentCourse: SchoolModel) {
        schoolDao.delete(studentCourse)
    }

    suspend fun deleteAllSchool() {
        schoolDao.deleteAllSchool()
    }
}