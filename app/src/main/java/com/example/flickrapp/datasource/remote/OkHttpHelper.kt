package com.example.flickrapp.datasource.remote

import android.util.Log
import com.example.flickrapp.PhotoObserver
import com.example.flickrapp.model.PhotoResponse.PhotoResult
import com.google.gson.Gson
import okhttp3.*
import org.greenrobot.eventbus.EventBus
import java.io.IOException
import java.util.*

class OkHttpHelper
{
    fun getClient() : OkHttpClient
    {
        val okHttpClient = OkHttpClient.Builder().build()
        return okHttpClient
    }

    fun makeAsyncApiCall(url : String)
    {
        val request = Request.Builder().url(url).build()
        getClient().newCall(request).enqueue(object  : Callback
        {
            override fun onResponse(call: Call, response: Response)
            {
                val json = response.body?.string()
                val photoResult =
                    Gson().fromJson<PhotoResult>(json, PhotoResult::class.java)
                EventBus.getDefault().post(photoResult)
            }

            override fun onFailure(call: Call, e: IOException)
            {
                Log.e("ERROR_TAG", "Error in makeAsyncApiCall ---->", e)
            }
        })
    }

}