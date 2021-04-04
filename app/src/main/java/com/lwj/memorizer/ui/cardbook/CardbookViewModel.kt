package com.lwj.memorizer.ui.cardbook

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lwj.memorizer.Event
import com.lwj.memorizer.R
import com.lwj.memorizer.base.BaseViewModel
import com.lwj.memorizer.data.Repository
import com.lwj.memorizer.data.entities.Cardbook
import com.lwj.memorizer.ext.runEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardbookViewModel @Inject constructor(private val repository: Repository) : BaseViewModel() {

    val title = MutableLiveData<String>()

    val isBookmarked = MutableLiveData<Boolean>()

    val coverImageUri = MutableLiveData<String>()

    private val _openExitDialog = MutableLiveData<Event<String>>()
    val openExitDialog: LiveData<Event<String>>
        get() = _openExitDialog

    private val _actionHideKeyboard = MutableLiveData<Event<Unit>>()
    val actionHideKeyboard: LiveData<Event<Unit>>
        get() = _actionHideKeyboard

    private val _actionFocusOnTitle = MutableLiveData<Event<Unit>>()
    val actionFocusOnTitle: LiveData<Event<Unit>>
        get() = _actionFocusOnTitle

    private val _actionFinish = MutableLiveData<Event<Unit>>()
    val actionFinish: LiveData<Event<Unit>>
        get() = _actionFinish

    private var isNewCardbook: Boolean = false

    private val resultModel = Cardbook()

    private var originalTitle: String? = null

    fun setItem(key: Long) {
        if(key == -1L) {
            isNewCardbook = true
        } else {
            resultModel.idx = key
        }
        loadCardbook(key)
    }

    private fun loadCardbook(key: Long) {
        viewModelScope.launch {
            repository.getCardbook(key)?.let { data ->
                originalTitle = data.title
                title.value = data.title
                isBookmarked.value = data.isBookmarked
                data.coverImageUri?.let {
                    coverImageUri.value = it
                }
            }
        }
    }

    private fun saveNewCardbook(isNewCardbook: Boolean, model: Cardbook) {
        viewModelScope.launch {
            if (isNewCardbook) repository.insertCardbook(model) else repository.updateCardbook(model)
        }
        _actionFinish.runEvent()
    }

    fun onBookmarkClicked() {
        isBookmarked.value?.let {
            isBookmarked.value = !it
        }
    }

    fun onSaveButtonClicked() {
        setResult()
        if (isTitleEmpty) focusOnTitle()
        else saveNewCardbook(isNewCardbook, resultModel)
    }

    private val isTitleEmpty: Boolean
        get() {
            title.value?.let {
                return it.isEmpty()
            } ?: return false
        }

    private fun setResult() {
        resultModel.apply {
            this@CardbookViewModel.title.value?.let {
                title = it
            }
            this@CardbookViewModel.isBookmarked.value?.let {
                isBookmarked = it
            }
            this@CardbookViewModel.coverImageUri.value?.let {
                coverImageUri = it
            }
        }
    }

    private fun focusOnTitle() {
        _actionFocusOnTitle.runEvent()
        onToast(R.string.msg_enter_title)
    }

    fun onBackPressed() {
        originalTitle?.let {
            _openExitDialog.value = Event(it)
        } ?: run{
            _actionFinish.runEvent()
        }
    }
}