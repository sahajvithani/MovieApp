package com.catalystmi.movieapp.data.model

import com.google.gson.annotations.SerializedName

data class PlayVideoResponse(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("results") var results: ArrayList<Results1> = arrayListOf()
)

data class Results1(
    @SerializedName("iso_639_1") var iso6391: String? = null,
    @SerializedName("iso_3166_1") var iso31661: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("key") var key: String? = null,
    @SerializedName("site") var site: String? = null,
    @SerializedName("size") var size: Int? = null,
    @SerializedName("type") var type: String? = null,
    @SerializedName("official") var official: Boolean? = null,
    @SerializedName("published_at") var publishedAt: String? = null,
    @SerializedName("id") var id: String? = null
)
