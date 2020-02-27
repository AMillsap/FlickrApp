package com.example.flickrapp.model.PhotoResponse

import android.os.Parcelable
import com.example.flickrapp.model.PhotoResponse.Photo
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Photos(
    @SerializedName("page")
    val page: Int,
    @SerializedName("pages")
    val pages: String,
    @SerializedName("perpage")
    val perpage: Int,
    @SerializedName("photo")
    val photo: ArrayList<Photo>,
    @SerializedName("total")
    val total: String
):Parcelable