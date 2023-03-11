package com.example.chiefcuisine.retrofit

import com.example.chiefcuisine.apiresponse.*
import com.example.chiefcuisine.utils.Constants.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MealApi {
    @GET("recipes/random")
    suspend fun getRandomMeal(
        @Query("apiKey") apiKey: String=API_KEY,
        @Query("number") number: String,
        @Query("tags") tags: String
    ): Response<RandomMeal>

    @GET("recipes/findByNutrients")
    suspend fun getRecommendedMeal(
        @Query("apiKey") apiKey: String=API_KEY,
        @Query("number") number: String,
        @Query("minCarbs") minCarbs: String,
        @Query("maxCarbs") maxCarbs: String,
    ): Response<RecommendedFood>

    @GET("recipes/{id}/ingredientWidget.json")
    suspend fun getMealIngredients(
        @Path("id") id: Int,
        @Query("apiKey") apiKey: String=API_KEY

    ): Response<IngredientsInfo>

    @GET("recipes/{id}/similar")
    suspend fun getSimilarFoodItems(
        @Path("id") id: Int,
        @Query("apiKey") apiKey: String=API_KEY

    ): Response<SimilarItems>

    @GET("recipes/{id}/information")
    suspend fun getInformationByID(
        @Path("id") id: Int,
        @Query("apiKey") apiKey: String=API_KEY
    ): Response<Recipe>


}