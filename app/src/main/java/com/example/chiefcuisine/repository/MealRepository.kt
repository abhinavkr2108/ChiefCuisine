package com.example.chiefcuisine.repository

import com.example.chiefcuisine.apiresponse.IngredientsInfo
import com.example.chiefcuisine.apiresponse.RandomMeal
import com.example.chiefcuisine.apiresponse.RecommendedFood
import com.example.chiefcuisine.apiresponse.SimilarItems
import com.example.chiefcuisine.retrofit.RetrofitInstance
import retrofit2.Response

class MealRepository {
    suspend fun getRandomMeal(number: String): Response<RandomMeal>{
        return  RetrofitInstance.api.getRandomMeal(number = number, tags = "vegetarian,dessert")
    }

    suspend fun getRecommendedMeal(number: String, minCarbs: String, maxCarbs: String): Response<RecommendedFood>{
        return RetrofitInstance.api.getRecommendedMeal(number = number, minCarbs = minCarbs, maxCarbs = maxCarbs)
    }

    suspend fun getIngredients(id: Int): Response<IngredientsInfo>{
        return RetrofitInstance.api.getMealIngredients(id = id)
    }

    suspend fun getSimilarFoodItems(id: Int): Response<SimilarItems>{
        return RetrofitInstance.api.getSimilarFoodItems(id = id)
    }

    suspend fun getInformationById(id: Int) = RetrofitInstance.api.getInformationByID(id = id)

    suspend fun getFoodByCategory(number: String, category: String): Response<RandomMeal>{
        return RetrofitInstance.api.getRandomMeal(number = number, tags = category)
    }
}