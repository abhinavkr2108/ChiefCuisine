package com.example.chiefcuisine.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.chiefcuisine.repository.MealRepository

class DetailsViewModelFactory(private val app: Application, private val repository: MealRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailsViewModel(app, repository) as T
    }
}