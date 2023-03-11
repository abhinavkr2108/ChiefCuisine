package com.example.chiefcuisine.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.chiefcuisine.apiresponse.IngredientsInfo
import com.example.chiefcuisine.apiresponse.RandomMeal
import com.example.chiefcuisine.apiresponse.Recipe
import com.example.chiefcuisine.apiresponse.SimilarItems
import com.example.chiefcuisine.repository.MealRepository
import kotlinx.coroutines.launch

class DetailsViewModel(app: Application, private val repository: MealRepository): AndroidViewModel(app) {

    val ingredientsLiveData = MutableLiveData<IngredientsInfo>()
    val similarItemsLiveData = MutableLiveData<SimilarItems>()
//    val categoryItemsLiveData = MutableLiveData<RandomMeal>()



    fun getIngredients(id: Int) = viewModelScope.launch {
        val ingredientResponse = repository.getIngredients(id)
        ingredientsLiveData.postValue(ingredientResponse.body())
    }

    fun getSimilarFoodItems(id: Int) = viewModelScope.launch {
        val similarItemsResponse = repository.getSimilarFoodItems(id)
        similarItemsLiveData.postValue(similarItemsResponse.body())
    }

//    fun getFoodByCategory(number: String, category: String) = viewModelScope.launch {
//        val categoryResponse = repository.getFoodByCategory(number, category)
//        categoryItemsLiveData.postValue(categoryResponse.body())
//    }





}