package raum.muchbeer.jetpackcomposewithpaginglib.database

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow
import raum.muchbeer.jetpackcomposewithpaginglib.model.StudentModel

@Dao
interface StudentDao {

    @Query("SELECT * from student_table")
    fun getAllStudent(): Flow<List<StudentModel>>

    @Query("SELECT * from student_table where course_code = :id")
    fun getStudentById(id: Int) : StudentModel?

    @Insert
    suspend fun insert(item:StudentModel)

    @Update
    suspend fun update(item:StudentModel)

    @Delete
    suspend fun delete(item:StudentModel)

    @Query("DELETE FROM student_table")
    suspend fun deleteAllStudent()
}