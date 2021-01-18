package com.lwj.memorizer.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import com.lwj.memorizer.base.BaseViewModel
import com.lwj.memorizer.data.Repository

class HomeViewModel @ViewModelInject constructor(private val repository: Repository) : BaseViewModel() {
}