package com.lwj.memorizer.ui.cardbook

import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.lwj.memorizer.R
import com.lwj.memorizer.base.BaseFragment
import com.lwj.memorizer.databinding.FragmentCardbookBinding
import com.lwj.memorizer.ext.gone
import com.lwj.memorizer.ext.slideDown
import com.lwj.memorizer.ext.slideUp
import com.lwj.memorizer.ext.visible
import com.lwj.memorizer.ui.main.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CardBookFragment : BaseFragment<FragmentCardbookBinding>(
    R.layout.fragment_cardbook
) {

    private val viewModel : CardBookViewModel by viewModel()

    private val sharedViewModel : MainViewModel by sharedViewModel()

    override fun start() {

    }

    override fun setBinding() {
        binding.apply {
            vm = viewModel
            svm = sharedViewModel
        }
    }

    override fun onObserve() {
        with(sharedViewModel) {
            isSearchBarOpened.observe(viewLifecycleOwner, { isVisible ->
                if(isVisible) {
                    binding.svSearchCardbook.apply {
                        slideDown(requireContext())
                        visible()
                    }
                } else {
                    binding.svSearchCardbook.apply {
                        slideUp(requireContext())
                        gone()
                    }
                }
            })
        }
    }
}