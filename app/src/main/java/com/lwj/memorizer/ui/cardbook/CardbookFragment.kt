package com.lwj.memorizer.ui.cardbook

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.lwj.memorizer.R
import com.lwj.memorizer.base.BaseFragment
import com.lwj.memorizer.databinding.FragmentCardbookBinding
import com.lwj.memorizer.ext.gone
import com.lwj.memorizer.ext.slideDown
import com.lwj.memorizer.ext.slideUp
import com.lwj.memorizer.ext.visible
import com.lwj.memorizer.ui.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

class CardbookFragment : BaseFragment<FragmentCardbookBinding>(
    R.layout.fragment_cardbook
) {

    private val viewModel by viewModels<CardbookViewModel>()

    private val sharedViewModel by activityViewModels<MainViewModel>()

    private val adapter : CardbookAdapter by lazy {
        CardbookAdapter(viewModel)
    }

    override fun start() {

    }

    override fun setBinding() {
        binding.apply {
            vm = viewModel
            svm = sharedViewModel
            rvCardbookList.adapter = adapter
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