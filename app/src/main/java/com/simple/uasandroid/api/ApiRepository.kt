package com.simple.uasandroid.api

    import java.net.URL


    import retrofit2.Retrofit
    import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
    import retrofit2.converter.gson.GsonConverterFactory
    import com.simple.uasandroid.BuildConfig.BASE_URL
    import com.simple.uasandroid.BuildConfig.TSDB_API_KEY

class FootballApiService {

    companion object {
        fun getClient() : Retrofit{
            return Retrofit.Builder()
                .baseUrl("$BASE_URL$TSDB_API_KEY/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }
    }
}