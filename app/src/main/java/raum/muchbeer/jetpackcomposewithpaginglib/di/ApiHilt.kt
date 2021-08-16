package raum.muchbeer.jetpackcomposewithpaginglib.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import raum.muchbeer.jetpackcomposewithpaginglib.BuildConfig
import raum.muchbeer.jetpackcomposewithpaginglib.api.MovieService
import raum.muchbeer.jetpackcomposewithpaginglib.api.MovieInstance
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiHilt {

    @Singleton
    @Provides
    fun providesGSONBuilder(): Gson {
        return GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
    }

    @Singleton
    @Provides
    fun provideRetrofitBuilder(gson : Gson) : Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
    }


@Singleton
@Provides
fun provideDataCollectionService() : MovieService {
    return MovieInstance().dataInstance()
}
}