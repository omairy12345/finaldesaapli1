package com.example.finalfirebase.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalfirebase.model.MostpopularTvshowsResponse
import com.example.finalfirebase.model.Tvshow
import com.example.finalfirebase.service.EpisoDateService
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class EpisoDateViewModel(): ViewModel() {
    private val _tvShowList: MutableLiveData<List<Tvshow>> = MutableLiveData()
    val tvShowList:LiveData<List<Tvshow>> = _tvShowList

    private val error:MutableLiveData<String> = MutableLiveData()
    val _error:LiveData<String> =error;


    fun loadTvShows(){
        var service=EpisoDateService.getInstance()
        viewModelScope.launch {
            service.getmostpopularTvshows().enqueue(object :Callback<MostpopularTvshowsResponse> {
                override fun onResponse(
                    call: Call<MostpopularTvshowsResponse>,
                    response: Response<MostpopularTvshowsResponse>
                ) {
                    _tvShowList.postValue(response.body()!!.tvShows)
                }

                override fun onFailure(call: Call<MostpopularTvshowsResponse>, t: Throwable) {
                    error.postValue(t.message)
                }

            })
        }
    }

}
