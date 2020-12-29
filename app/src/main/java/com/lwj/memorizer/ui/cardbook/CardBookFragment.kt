package com.lwj.memorizer.ui.cardbook

import androidx.lifecycle.ViewModelProvider
import com.lwj.memorizer.R
import com.lwj.memorizer.base.BaseFragment
import com.lwj.memorizer.databinding.FragmentCardbookBinding

class CardBookFragment : BaseFragment<FragmentCardbookBinding>(
    R.layout.fragment_cardbook
) {

    private val viewModel by lazy {
        ViewModelProvider(this).get(CardBookViewModel::class.java)
    }

    override fun start() {
    }

    override fun setBinding() {
    }

    override fun onObserve() {
    }

}