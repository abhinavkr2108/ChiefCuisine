package com.example.chiefcuisine.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.chiefcuisine.repository.MealRepository

class HomeViewModelFactory(val app: Application, val repository: MealRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(app, repository) as T
    }
}