package com.lwj.memorizer.ui.cardbooklist

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.lwj.memorizer.base.BaseViewModel
import com.lwj.memorizer.data.Repository
import com.lwj.memorizer.data.entities.CardbookList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardbookListViewModel @Inject constructor(private val repository: Repository) : BaseViewModel() {

    val cardbookListList: LiveData<List<CardbookList>> = repository.getAllCardbooks().asLiveData()

    fun onCardbookItemClicked(key: Long) {
        removeCardbook(key)
    }

    fun removeCardbook(key: Long) {
        viewModelScope.launch {
            repository.removeCardbook(key)
        }
    }

}