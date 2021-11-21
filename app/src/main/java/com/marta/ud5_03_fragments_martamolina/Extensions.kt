package com.marta.ud5_03_fragments_martamolina

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.constraintlayout.helper.widget.MotionPlaceholder
import com.marta.ud5_03_fragments_martamolina.model.User
import com.squareup.picasso.Picasso
import java.util.*

fun ImageView.imageUrl(imageUrl: String) {
    Picasso.get().load(imageUrl)
        .placeholder(R.drawable.ic_launcher_foreground)
        .into(this)
}
fun concatAndFormatFullName (user: User): String {
    return  (user.name?.title)?.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() } + " " + (user.name?.first)?.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(
            Locale.getDefault()
        ) else it.toString()
    } + " " + (user.name?.last)?.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
}