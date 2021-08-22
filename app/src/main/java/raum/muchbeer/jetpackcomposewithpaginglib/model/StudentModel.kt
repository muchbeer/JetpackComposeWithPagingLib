package raum.muchbeer.jetpackcomposewithpaginglib.model

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.room.Entity
import androidx.room.PrimaryKey
import raum.muchbeer.jetpackcomposewithpaginglib.R

@Entity(tableName = "student_table")
data class StudentModel(
     val course_name : String,
     var isDone : Boolean,
    @PrimaryKey(autoGenerate = true)
     val course_code : Int = 0
)
