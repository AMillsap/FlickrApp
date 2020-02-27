package com.example.flickrapp.view.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flickrapp.DescriptionActivity
import com.example.flickrapp.R
import com.example.flickrapp.model.PhotoResponse.Photo
import kotlinx.android.synthetic.main.photo_item.view.*

class PictureAdapter() : RecyclerView.Adapter<PictureAdapter.ViewHolder>()
{
    var pictureList = ArrayList<Photo>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder((LayoutInflater.from(parent.context).inflate(R.layout.photo_item, parent, false)))

    override fun getItemCount(): Int = pictureList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        holder.populateItem(pictureList[position])
    }

    fun updateList(list : ArrayList<Photo>)
    {
        pictureList = list
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
    {
        fun populateItem(picture : Photo)
        {
            itemView.tvTitleOfPicture.text = picture.title
            var pictureUrl = "https://farm${picture.farm}.staticflickr.com/${picture.server}/${picture.id}_${picture.secret}.jpg"
            Glide
                .with(itemView)
                .load(pictureUrl)
                .into(itemView.ivPictureImage)
            itemView.setOnClickListener {
                val intent = Intent(it.context, DescriptionActivity::class.java)
                intent.putExtra("KEY", picture)
                it.context.startActivity(intent)
            }
        }

    }
}