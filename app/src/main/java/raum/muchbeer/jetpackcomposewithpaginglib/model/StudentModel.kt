package raum.muchbeer.jetpackcomposewithpaginglib.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student_table")
data class StudentModel(
     val course_name : String,
     var isDone : Boolean,
    @PrimaryKey(autoGenerate = true)
     val course_code : Long = 0L
) {
}