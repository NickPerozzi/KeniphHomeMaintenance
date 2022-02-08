package com.example.keniphhomemaintenance.dwellings

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DwellingsViewModelFactory(private val context: Context) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T = DwellingsViewModel(context) as T

}