package com.example.flickrapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.example.flickrapp.model.PhotoResponse.Photo
import kotlinx.android.synthetic.main.activity_description.*
import kotlinx.android.synthetic.main.photo_item.view.*

class DescriptionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)
        val passedPhoto = intent?.getParcelableExtra<Photo>("KEY")
        tvTitle.text = "Title: "+ passedPhoto?.title
        tvAuthorID.text = "Author: " + passedPhoto?.owner
        tvPictureID.text = "Photo ID: " + passedPhoto?.id
        var pictureUrl = "https://farm${passedPhoto?.farm}.staticflickr.com/${passedPhoto?.server}/${passedPhoto?.id}_${passedPhoto?.secret}.jpg"
        Glide
            .with(this)
            .load(pictureUrl)
            .into(ivImage)
    }
}
