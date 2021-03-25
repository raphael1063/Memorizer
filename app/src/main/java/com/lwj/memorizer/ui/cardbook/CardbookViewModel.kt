package com.lwj.memorizer.ui.cardbook

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lwj.memorizer.Event
import com.lwj.memorizer.R
import com.lwj.memorizer.base.BaseViewModel
import com.lwj.memorizer.data.Repository
import com.lwj.memorizer.data.entities.CardbookList
import com.lwj.memorizer.ext.runEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardbookViewModel@Inject constructor(private val repository: Repository) : BaseViewModel(){

    val cardbookTitle = MutableLiveData("")

    private val _actionHideKeyboard = MutableLiveData<Event<Unit>>()
    val actionHideKeyboard: LiveData<Event<Unit>>
        get() = _actionHideKeyboard

    private val _actionFocusOnTitle = MutableLiveData<Event<Unit>>()
    val actionFocusOnTitle: LiveData<Event<Unit>>
        get() = _actionFocusOnTitle

    private val _actionFinish = MutableLiveData<Event<Unit>>()
    val actionFinish: LiveData<Event<Unit>>
        get() = _actionFinish

    private fun saveCardbook(title: String) {
        viewModelScope.launch {
            repository.insertCardbook(CardbookList(title, false, null))
        }
    }

    fun onSaveButtonClicked() {
        cardbookTitle.value?.let {  title ->
            if(title.isEmpty()) {
                _actionFocusOnTitle.runEvent()
                onToast(R.string.empty_cardbook_title)
            } else {
                saveCardbook(title)
                _actionFinish.runEvent()
            }
        }
    }
}