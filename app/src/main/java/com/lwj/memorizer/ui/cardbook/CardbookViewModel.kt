package com.lwj.memorizer.ui.cardbook

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.lwj.memorizer.base.BaseViewModel
import com.lwj.memorizer.data.Repository
import com.lwj.memorizer.data.entities.Cardbook
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardbookViewModel @Inject constructor(private val repository: Repository) : BaseViewModel() {

    val cardbookList: LiveData<List<Cardbook>> = repository.getAllCardbooks().asLiveData()

    fun removeCardbook(key: Long) {
        viewModelScope.launch {
            repository.removeCardbook(key)
        }
    }

}