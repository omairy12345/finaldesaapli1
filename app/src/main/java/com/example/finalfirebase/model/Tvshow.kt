package com.example.finalfirebase.model

import com.google.gson.annotations.SerializedName

class Tvshow {
    var id: Long = 0
    var name: String = ""

    @SerializedName("image_thumbnail_path")
    val image: String=""

    @SerializedName("end_date")
    val StartDate:String=""


    val Country:String=""
    val network:String=""
    val status:String=""


}

class MostpopularTvshowsResponse{
    var total: Int=0
    var page: Int=1
    var pages: Int=0

    @SerializedName("tv shows")
    var tvShows:List<Tvshow>?=null
}



