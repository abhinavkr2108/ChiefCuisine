package com.example.chiefcuisine.viewmodels

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.chiefcuisine.apiresponse.MealInfo
import com.example.chiefcuisine.apiresponse.RandomMeal
import com.example.chiefcuisine.apiresponse.Recipe
import com.example.chiefcuisine.apiresponse.RecommendedFood
import com.example.chiefcuisine.repository.MealRepository
import kotlinx.coroutines.launch

class HomeViewModel(app: Application, val repository: MealRepository): AndroidViewModel(app) {
    val randomMealLiveData = MutableLiveData<RandomMeal>()
    val recommendedMealLiveData = MutableLiveData<RecommendedFood>()
    val infoResponseLiveData = MutableLiveData<Recipe>()
    val categoryItemsLiveData = MutableLiveData<RandomMeal>()

    init {
        getRandomMeal()
        getRecommendedMeal()
    }

    private fun getRandomMeal() = viewModelScope.launch {
        repository.getRandomMeal("10")
        val topPicksResponse = repository.getRandomMeal("10")
        if (topPicksResponse.isSuccessful){
            randomMealLiveData.postValue(topPicksResponse.body())
        }
    }

    private fun getRecommendedMeal() = viewModelScope.launch {
        val recommendedResponse = repository.getRecommendedMeal("15", "10", "100")
        recommendedMealLiveData.postValue(recommendedResponse.body())
    }

    fun getInformationById(id: Int) = viewModelScope.launch {
        val infoResponse = repository.getInformationById(id)
        infoResponseLiveData.postValue(infoResponse.body())
    }

    fun getFoodByCategory(number: String, category: String) = viewModelScope.launch {
        val categoryResponse = repository.getFoodByCategory(number, category)
        categoryItemsLiveData.postValue(categoryResponse.body())
    }



}