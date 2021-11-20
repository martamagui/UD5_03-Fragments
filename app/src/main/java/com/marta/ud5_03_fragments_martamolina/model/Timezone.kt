package com.marta.ud5_03_fragments_martamolina.model


import com.google.gson.annotations.SerializedName

data class Timezone(
    @SerializedName("offset")
    val offset: String,
    @SerializedName("description")
    val description: String
)