package com.example.chiefcuisine.utils

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CategoryItems(
    val id: Int,
    val image: Int,
    val categoryName: String
): Parcelable
