package com.example.fetchrewardsexercise.ui.activity

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fetchrewards.models.NameObject
import com.example.fetchrewardsexercise.repository.NameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: NameRepository ) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getNames()
        }
    }

    val names : LiveData<NameObject> = repository.nameLiveData
}