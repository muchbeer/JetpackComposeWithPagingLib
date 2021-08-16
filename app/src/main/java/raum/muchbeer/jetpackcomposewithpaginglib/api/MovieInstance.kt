package raum.muchbeer.jetpackcomposewithpaginglib.api

import android.util.Log
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import raum.muchbeer.jetpackcomposewithpaginglib.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieInstance {
    companion object {

        // val BASE_URL = "https://api.themoviedb.org/3/"

        val httpLogger = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY }

        val client = OkHttpClient.Builder().apply {

            this.addInterceptor(httpLogger)
                .retryOnConnectionFailure(true)
                .readTimeout(10, java.util.concurrent.TimeUnit.SECONDS)
                .connectTimeout(10, java.util.concurrent.TimeUnit.SECONDS)
        }.build()
    }

    fun dataInstance() : MovieService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(MovieService::class.java)
    }
}