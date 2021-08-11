package raum.muchbeer.jetpackcomposewithpaginglib.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import raum.muchbeer.jetpackcomposewithpaginglib.model.StudentModel

@Database(entities = [StudentModel::class], version = 1, exportSchema = false)
abstract class StudentDatabase : RoomDatabase(){

    abstract fun studentDao() : StudentDao

    companion object {

        private var INSTANCE: StudentDatabase? = null

        fun getInstance(context: Context): StudentDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        StudentDatabase::class.java,
                        "todo_list_database"
                    ).fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }

                return instance
            }
        }

    }
}
