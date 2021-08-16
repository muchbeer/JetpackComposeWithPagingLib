package raum.muchbeer.jetpackcomposewithpaginglib.di

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import raum.muchbeer.jetpackcomposewithpaginglib.BuildConfig
import raum.muchbeer.jetpackcomposewithpaginglib.api.MovieService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


fun provideHeaderInterceptor(): Interceptor =
    Interceptor { chain ->
        val request = chain.request()
        val newUrl = request.url.newBuilder()
            .addQueryParameter("api_key", BuildConfig.API_KEY)
            .build()

        val newRequest = request.newBuilder()
            .url(newUrl)
            .build()
        chain.proceed(newRequest)
    }

val networkModule = module {
    single { provideHeaderInterceptor() }
    single { okhttpClient(get()) }
    single { retrofit(get()) }
    single { apiService(get()) }
}

fun apiService(
    retrofit: Retrofit
): MovieService =
    retrofit.create(MovieService::class.java)

fun retrofit(
    okHttpClient: OkHttpClient
): Retrofit =
    Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

fun okhttpClient(
    headerInterceptor: Interceptor
): OkHttpClient =
    OkHttpClient.Builder()
        .addInterceptor(headerInterceptor)
        .build()