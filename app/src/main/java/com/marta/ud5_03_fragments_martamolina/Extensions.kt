package com.marta.ud5_03_fragments_martamolina

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.constraintlayout.helper.widget.MotionPlaceholder
import com.squareup.picasso.Picasso

fun ImageView.imageUrl(imageUrl: String) {
    Picasso.get().load(imageUrl)
        .placeholder(R.drawable.ic_launcher_foreground)
        .into(this)
}