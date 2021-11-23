package com.marta.ud5_03_fragments_martamolina

import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.constraintlayout.helper.widget.MotionPlaceholder
import com.marta.ud5_03_fragments_martamolina.model.Result
import com.marta.ud5_03_fragments_martamolina.model.User
import com.marta.ud5_03_fragments_martamolina.network.RandomUserApi
import com.marta.ud5_03_fragments_martamolina.network.RandomUserApi.service
import com.squareup.picasso.Picasso
import com.squareup.picasso.Picasso.get
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

fun ImageView.imageUrl(imageUrl: String) {
    Picasso.get().load(imageUrl)
        .placeholder(R.drawable.ic_launcher_foreground)
        .into(this)
}

fun concatAndFormatFullName(user: User): String {
    return (user.name?.title)?.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() } + " " + (user.name?.first)?.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(
            Locale.getDefault()
        ) else it.toString()
    } + " " + (user.name?.last)?.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
}

fun codeToCountry(code: String): String? {
    // v1.3: AU, BR, CA, CH, DE, DK, ES, FI, FR, GB, IE, IR, NO, NL, NZ, TR, US

    val countries: HashMap<String, String> = hashMapOf(
        "AU" to "Australia",
        "BR" to "Brazil",
        "CA" to "Canada",
        "CH" to "Switzerland",
        "DE" to "Germany",
        "DK" to "Denmark",
        "ES" to "Spain",
        "FI" to "Finland",
        "FR" to "France",
        "GB" to "Great Britain",
        "IE" to "Ireland",
        "IR" to "Iran",
        "NO" to "Norway",
        "NL" to "Netherlands",
        "NZ" to "New Zealand",
        "TR" to "Turkey",
        "US" to "United States"
    )
    return countries[code]
}

fun formatDate(date: String): String {
    return date.substring(0, 10)
}