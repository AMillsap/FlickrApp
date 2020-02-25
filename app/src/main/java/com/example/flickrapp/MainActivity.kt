package com.example.flickrapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flickrapp.datasource.remote.OkHttpHelper
import com.example.flickrapp.model.PhotoResponse.PhotoResult
import com.example.flickrapp.view.adapter.PictureAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : AppCompatActivity()
{
    var searchTags = " "
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart()
    {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop()
    {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onUserResponse(photoResult: PhotoResult)
    {
        rvPictureList.layoutManager = LinearLayoutManager(this)
        val itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        rvPictureList.addItemDecoration((itemDecoration))
        rvPictureList.adapter = PictureAdapter(photoResult.photos.photo)
        (rvPictureList.adapter as PictureAdapter).updateList()
    }

    fun onClick(view: View)
    {
        searchTags = etPictureSearch.text.toString()
        val url =
            "https://www.flickr.com/services/rest/?method=flickr.photos.search&api_key=6bf318919bbbc455f3573d18798a58e3&" +
                    "tags=$searchTags&media=photos&format=json&nojsoncallback=1"
        val okHttpHelper = OkHttpHelper()
        okHttpHelper.makeAsyncApiCall(url)
    }


}
