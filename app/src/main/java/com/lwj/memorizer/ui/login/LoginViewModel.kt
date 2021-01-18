package com.lwj.memorizer.ui.login

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lwj.memorizer.Event
import com.lwj.memorizer.base.BaseViewModel
import com.lwj.memorizer.data.Repository

class LoginViewModel @ViewModelInject constructor(private val repository: Repository) : BaseViewModel() {

    private val _actionLogin = MutableLiveData<Event<Unit>>()
    val actionLogin: LiveData<Event<Unit>>
        get() = _actionLogin

    fun onLogin() {
        _actionLogin.value = Event(Unit)
    }
}