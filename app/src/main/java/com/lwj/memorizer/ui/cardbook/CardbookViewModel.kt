package com.lwj.memorizer.ui.cardbook

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.lwj.memorizer.base.BaseViewModel
import com.lwj.memorizer.data.Repository
import com.lwj.memorizer.data.entities.Cardbook

class CardbookViewModel @ViewModelInject constructor(private val repository: Repository, @Assisted private val savedStateHandle: SavedStateHandle) : BaseViewModel() {

    private val _cardbookList = MutableLiveData<ArrayList<Cardbook>>()
    val cardbookList: LiveData<ArrayList<Cardbook>>
        get() = _cardbookList

    init {
        _cardbookList.value = arrayListOf<Cardbook>().apply {
            add(Cardbook(0, "title1", false, null))
            add(Cardbook(1, "title2", false, null))
            add(Cardbook(2, "title3", false, null))
            add(Cardbook(3, "title4", false, null))
            add(Cardbook(4, "title5", false, null))
            add(Cardbook(5, "title6", false, null))
            add(Cardbook(6, "title7", false, null))
            add(Cardbook(7, "title8", false, null))
            add(Cardbook(8, "title9", false, null))
            add(Cardbook(9, "title10", false, null))
        }
    }
}