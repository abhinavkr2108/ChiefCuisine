package com.example.chiefcuisine.apiresponse

data class RecommendedFoodItems(
    val calories: Int,
    val carbs: String,
    val fat: String,
    val id: Int,
    val image: String,
    val imageType: String,
    val protein: String,
    val title: String
): java.io.Serializable