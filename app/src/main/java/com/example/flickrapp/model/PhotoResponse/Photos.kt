package com.example.flickrapp.model.PhotoResponse

import com.example.flickrapp.model.PhotoResponse.Photo

data class Photos(
    val page: Int,
    val pages: String,
    val perpage: Int,
    val photo: List<Photo>,
    val total: String
)