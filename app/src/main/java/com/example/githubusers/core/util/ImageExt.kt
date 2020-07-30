package com.example.githubusers.core.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

fun ImageView.load(imageSrc: Any) {
    Glide.with(context)
        .load(imageSrc)
        .apply(RequestOptions().transform(RoundedCorners(14)))
        .into(this)

}