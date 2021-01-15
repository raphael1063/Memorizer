package com.lwj.memorizer.ui.cardbook

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lwj.memorizer.base.BaseViewModel
import com.lwj.memorizer.data.Repository
import com.lwj.memorizer.data.entities.Cardbook

class CardbookViewModel @ViewModelInject constructor() : BaseViewModel() {

    private val _cardbookList = MutableLiveData<ArrayList<Cardbook>>()
    val cardbookList: LiveData<ArrayList<Cardbook>>
        get() = _cardbookList
}