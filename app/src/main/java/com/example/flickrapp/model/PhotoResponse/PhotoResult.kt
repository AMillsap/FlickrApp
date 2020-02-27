package com.example.flickrapp.model.PhotoResponse

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PhotoResult(
    @SerializedName("Photos")
    val photos: Photos,
    @SerializedName("stat")
    val stat: String
) : Parcelable