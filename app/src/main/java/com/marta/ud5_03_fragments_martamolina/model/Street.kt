package com.marta.ud5_03_fragments_martamolina.model


import com.google.gson.annotations.SerializedName

data class Street(
    @SerializedName("number")
    val number: Int,
    @SerializedName("name")
    val name: String
)