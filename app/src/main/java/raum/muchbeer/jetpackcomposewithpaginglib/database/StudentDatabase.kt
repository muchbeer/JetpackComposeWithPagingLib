package raum.muchbeer.jetpackcomposewithpaginglib.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import raum.muchbeer.jetpackcomposewithpaginglib.model.SchoolModel
import raum.muchbeer.jetpackcomposewithpaginglib.model.StudentModel

@Database(entities = [StudentModel::class, SchoolModel::class], version = 2, exportSchema = false)
abstract class StudentDatabase : RoomDatabase(){

    abstract fun studentDao() : StudentDao
    abstract fun schoolDao() : SchoolDao

    companion object {

        private var INSTANCE: StudentDatabase? = null

        fun getInstance(context: Context): StudentDatabase {
            Log.d("AddView", "ViewStuff")

            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        StudentDatabase::class.java,
                        "muchbeer_compose_database"
                    ).fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }

                return instance
            }
        }

    }
}
