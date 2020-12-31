package com.lwj.memorizer.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lwj.memorizer.Event
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

    private val _isCardBookView = MutableLiveData<Boolean>()
    val isCardBookView: LiveData<Boolean>
        get() = _isCardBookView

    private val _isSearchMenuActivate = MutableLiveData<Boolean>()
    val isSearchMenuActivate: LiveData<Boolean>
        get() = _isSearchMenuActivate

    /* Action */
    private val _isSearchBarOpened = MutableLiveData(false)
    val isSearchBarOpened: LiveData<Boolean>
        get() = _isSearchBarOpened

    private val _actionSettingButtonClicked = MutableLiveData<Event<Unit>>()
    val actionSettingButtonClicked: LiveData<Event<Unit>>
        get() = _actionSettingButtonClicked

    private val _actionSupportButtonClicked = MutableLiveData<Event<Unit>>()
    val actionSupportButtonClicked: LiveData<Event<Unit>>
        get() = _actionSupportButtonClicked

    private val _actionAboutButtonClicked = MutableLiveData<Event<Unit>>()
    val actionAboutButtonClicked: LiveData<Event<Unit>>
        get() = _actionAboutButtonClicked

    fun setCurrentNavId(id: Int) {
        when(id) {
            R.id.nav_home -> {
                _currentViewPagerItem.value = 0
                _toolbarTitle.value = "Home"
                _isCardBookView.value = false
            }
            R.id.nav_cardbook -> {
                _currentViewPagerItem.value = 1
                _toolbarTitle.value = "Cardbook"
                _isCardBookView.value = true
            }
            R.id.nav_training -> {
                _currentViewPagerItem.value = 2
                _toolbarTitle.value = "Training"
                _isCardBookView.value = false
            }
            R.id.nav_my_account -> {
                _currentViewPagerItem.value = 3
                _toolbarTitle.value = "My Account"
                _isCardBookView.value = false
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

    fun onSearchMenuClicked() {
        isSearchBarOpened.value?.let {
            _isSearchBarOpened.value = !it
            _isSearchMenuActivate.value = !it
        }
    }

    fun onSettingMenuClicked() {
        _actionSettingButtonClicked.value = Event(Unit)
    }

    fun onSupportMenuClicked() {
        _actionSupportButtonClicked.value = Event(Unit)
    }

    fun onAboutMenuClicked() {
        _actionAboutButtonClicked.value = Event(Unit)
    }
}