package com.lwj.memorizer.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lwj.memorizer.R
import com.lwj.memorizer.base.BaseViewModel
import com.lwj.memorizer.data.Repository

class MainViewModel(private val repository: Repository) : BaseViewModel() {

    private val _currentNavigationItem = MutableLiveData<Int>()
    val currentNavigationItem: LiveData<Int>
        get() = _currentNavigationItem

    private val _currentViewPagerItem = MutableLiveData<Int>()
    val currentViewPagerItem: LiveData<Int>
        get() = _currentViewPagerItem

    private val _toolbarTitle = MutableLiveData<String>()
    val toolbarTitle: LiveData<String>
        get() = _toolbarTitle

    fun setCurrentNavId(id: Int) {
        when(id) {
            R.id.nav_home -> {
                _currentViewPagerItem.value = 0
                _toolbarTitle.value = "Home"
            }
            R.id.nav_cardbook -> {
                _currentViewPagerItem.value = 1
                _toolbarTitle.value = "Cardbook"
            }
            R.id.nav_training -> {
                _currentViewPagerItem.value = 2
                _toolbarTitle.value = "Training"
            }
            R.id.nav_my_account -> {
                _currentViewPagerItem.value = 3
                _toolbarTitle.value = "My Account"
            }
        }
    }

    fun setCurrentViewPagerPosition(position: Int) {
        when (position) {
            0 -> _currentNavigationItem.value = R.id.nav_home
            1 -> _currentNavigationItem.value = R.id.nav_cardbook
            2 -> _currentNavigationItem.value = R.id.nav_training
            3 -> _currentNavigationItem.value = R.id.nav_my_account
        }
    }
}