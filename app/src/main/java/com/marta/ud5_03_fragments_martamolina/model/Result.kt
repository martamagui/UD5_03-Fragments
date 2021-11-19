package com.marta.ud5_03_fragments_martamolina.model

import com.google.gson.annotations.SerializedName

data class Result(
    val info: Info,
    @SerializedName("results")
    val results: List<User>
)