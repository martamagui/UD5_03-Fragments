package com.marta.ud5_03_fragments_martamolina.model


import com.google.gson.annotations.SerializedName

data class Id(
    @SerializedName("name")
    val name: String,
    @SerializedName("value")
    val value: String?
)