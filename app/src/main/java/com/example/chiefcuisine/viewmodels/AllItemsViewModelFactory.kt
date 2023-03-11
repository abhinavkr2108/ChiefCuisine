package com.example.chiefcuisine.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.chiefcuisine.repository.MealRepository

class AllItemsViewModelFactory(private val app: Application, private val repository: MealRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AllItemsViewModel(app, repository) as T
    }
}