package com.raihan.inventory.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val id: Int,
    val name: String,
    val price: Int,
    @SerializedName("image_url") val imageUrl: String) : Parcelable
