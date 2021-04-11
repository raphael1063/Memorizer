package com.lwj.memorizer.ui.cardbooklist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.lwj.memorizer.Event
import com.lwj.memorizer.base.BaseViewModel
import com.lwj.memorizer.data.Repository
import com.lwj.memorizer.data.entities.Cardbook
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardbookListViewModel @Inject constructor(private val repository: Repository) : BaseViewModel() {

    val cardbook: LiveData<List<Cardbook>> = repository.getAllCardbookLists().asLiveData()

    private val _openCardbook = MutableLiveData<Event<Long>>()
    val openCardbook: LiveData<Event<Long>>
        get() = _openCardbook

    private val _isDeleteMode = MutableLiveData(false)
    val isDeleteMode: LiveData<Boolean>
        get() = _isDeleteMode

    fun onCardbookItemClicked(key: Long) {
       openCardbook(key)
    }

    fun onCardbookItemLongClicked(key: Long) : Boolean{
        onToast("LongClicked")
        _isDeleteMode.value = true
        return true
    }

    private fun openCardbook(key: Long) {
        _openCardbook.value = Event(key)
    }

    private fun removeCardbook(key: Long) {
        viewModelScope.launch {
            repository.removeCardbook(key)
        }
    }
}