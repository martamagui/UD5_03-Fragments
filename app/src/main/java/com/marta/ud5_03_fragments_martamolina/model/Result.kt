package com.marta.ud5_03_fragments_martamolina.model


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("results")
    val results: List<User>,
    @SerializedName("info")
    val info: Info
)