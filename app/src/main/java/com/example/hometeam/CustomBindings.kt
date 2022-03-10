package com.example.hometeam

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


@BindingAdapter("loadimage")
fun bindingImage(userImageView: ImageView,imageUri:String){
    Glide.with(userImageView.context)
        .load(imageUri)
        .into(userImageView)
}