package com.example.hometeam

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("loadimage")
fun bindingImage(imageView: ImageView, imageUri: String) {
    if (imageUri !== "") {
        Glide.with(imageView.context)
            .load(imageUri).placeholder(R.drawable.player_image).error(R.drawable.player_image)
            .circleCrop()
            .into(imageView)
    }
}

@BindingAdapter("loadflag")
fun bindingFlagImage(imageView: ImageView, country: String) {
    if (country !== "") {
        Glide.with(imageView.context)
            .load("https://countryflagsapi.com/png/${country}").error(R.drawable.missing_flag)
            .into(imageView)
    }

}