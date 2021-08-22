package raum.muchbeer.jetpackcomposewithpaginglib.di

import android.app.Application
import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import raum.muchbeer.jetpackcomposewithpaginglib.database.StudentDatabase
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application ) : StudentDatabase {
        Log.d("AddView", "ViewStuff")
        return StudentDatabase.getInstance(app)
    }

    @Provides
    @Singleton
    fun provideStudentDao(db: StudentDatabase) = db.studentDao()

    @Provides
    @Singleton
    fun provideSchoolDao(db : StudentDatabase) = db.schoolDao()
}