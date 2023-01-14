package com.example.fetchrewardsexercise.ui.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fetchrewards.models.NameObject
import com.example.fetchrewardsexercise.repository.NameRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: NameRepository) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getNames()
        }
    }

    val names : LiveData<NameObject> = repository.nameLiveData
}