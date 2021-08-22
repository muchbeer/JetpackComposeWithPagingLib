package raum.muchbeer.jetpackcomposewithpaginglib.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import raum.muchbeer.jetpackcomposewithpaginglib.model.SchoolModel
import raum.muchbeer.jetpackcomposewithpaginglib.model.StudentModel

@Dao
interface SchoolDao {

    @Query("SELECT * from live_band_table")
    fun getAllSchool(): Flow<List<SchoolModel>>

    @Query("SELECT * from live_band_table where id = :id")
    fun getSchoolById(id: Int) : SchoolModel?

    @Insert
    suspend fun insert(item: SchoolModel)

    @Update
    suspend fun update(item: SchoolModel)

    @Delete
    suspend fun delete(item: SchoolModel)

    @Query("DELETE FROM live_band_table")
    suspend fun deleteAllSchool()

    @Query("UPDATE live_band_table SET schoolname =:course_name WHERE id=:course_id")
    suspend fun updateSchool(course_name : String, course_id: Int)
}
