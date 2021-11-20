package com.marta.ud5_03_fragments_martamolina.model


import com.google.gson.annotations.SerializedName

data class Dob(
    @SerializedName("date")
    val date: String,
    @SerializedName("age")
    val age: Int
)