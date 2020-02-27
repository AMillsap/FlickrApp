package com.example.flickrapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flickrapp.datasource.remote.OkHttpHelper
import com.example.flickrapp.datasource.remote.PhotoService
import com.example.flickrapp.model.PhotoResponse.Photo
import com.example.flickrapp.model.PhotoResponse.PhotoResult
import com.example.flickrapp.view.adapter.PictureAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : AppCompatActivity()
{
    var searchTags = " "
    val adapter by lazy { PictureAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvPictureList.layoutManager = LinearLayoutManager(this)
        val itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        rvPictureList.addItemDecoration((itemDecoration))
        rvPictureList.adapter = adapter
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
        adapter.updateList(photoResult.photos.photo)
    }

    fun onClick(view: View)
    {
        searchTags = etPictureSearch.text.toString()
        when(view.id)
        {
            R.id.btnGetPictures -> getPhoto()
        }
        /*val url =
            "https://www.flickr.com/services/rest/?method=flickr.photos.search&api_key=6bf318919bbbc455f3573d18798a58e3&" +
                    "tags=$searchTags&media=photos&format=json&nojsoncallback=1"
        val okHttpHelper = OkHttpHelper()
        okHttpHelper.makeAsyncApiCall(url)*/
    }

    private fun getPhoto()
    {
        PhotoService
            .getFlickrCallService()
            .getPhotos("flickr.photos.search","6bf318919bbbc455f3573d18798a58e3",
                searchTags,"photos", "json", "1")
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .map { photo -> updateList(photo) }
            .subscribe()
    }

    fun updateList(photoResult: PhotoResult)
    {
        Log.d("TAG", photoResult.toString())
        adapter.updateList(photoResult.photos.photo)
    }



}
