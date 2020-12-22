package com.lwj.memorizer.ui.cardbook

import androidx.lifecycle.ViewModelProvider
import com.lwj.memorizer.R
import com.lwj.memorizer.base.BaseActivity
import com.lwj.memorizer.base.BaseFragment
import com.lwj.memorizer.databinding.FragmentCardBookBinding

class CardBookFragment : BaseFragment<FragmentCardBookBinding>(
    R.layout.fragment_card_book
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