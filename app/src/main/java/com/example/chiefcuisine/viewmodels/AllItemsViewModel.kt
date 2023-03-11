package com.example.chiefcuisine.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.chiefcuisine.apiresponse.RandomMeal
import com.example.chiefcuisine.apiresponse.RecommendedFood
import com.example.chiefcuisine.repository.MealRepository
import kotlinx.coroutines.launch

class AllItemsViewModel(app: Application, private val repository: MealRepository): AndroidViewModel(app) {
    val allItemsLiveData = MutableLiveData<RandomMeal>()
    val allRecommendeItemsLiveData = MutableLiveData<RecommendedFood>()

    init {
        getRandomMeal()
        getRecommendedMeal()
    }

    private fun getRandomMeal() = viewModelScope.launch {
        val allItemsResponse = repository.getRandomMeal("50")
        allItemsLiveData.postValue(allItemsResponse.body())
    }

    private fun getRecommendedMeal() = viewModelScope.launch {
        val allRItemsResponse = repository.getRecommendedMeal("50", "10", "100")
        allRecommendeItemsLiveData.postValue(allRItemsResponse.body())
    }
}