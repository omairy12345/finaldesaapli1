package com.example.finalfirebase.service


import com.example.finalfirebase.model.MostpopularTvshowsResponse
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface EpisoDateService {
    @GET(value ="/api/most-popular")
    fun getmostpopularTvshows(@Query("page") page:Int=1) :Call<MostpopularTvshowsResponse>

    companion object{
        private var instance:EpisoDateService?=null

        fun getInstance():EpisoDateService {
            if (instance == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://www.episodate.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(OkHttpClient.Builder().build())
                    .build();

                instance = retrofit.create(EpisoDateService::class.java)
            }

                return instance!!;
        }
    }

}