package com.example.chiefcuisine.apiresponse


data class SimilarItemsItem(
    val id: Int,
    val imageType: String,
    val readyInMinutes: Int,
    val servings: Int,
    val sourceUrl: String,
    val title: String
):java.io.Serializable