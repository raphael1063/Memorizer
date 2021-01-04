package com.lwj.memorizer.ui.cardbook

import com.lwj.memorizer.R
import com.lwj.memorizer.base.BaseFragment
import com.lwj.memorizer.databinding.FragmentCardbookBinding
import com.lwj.memorizer.ext.gone
import com.lwj.memorizer.ext.slideDown
import com.lwj.memorizer.ext.slideUp
import com.lwj.memorizer.ext.visible
import com.lwj.memorizer.ui.main.MainViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CardbookFragment : BaseFragment<FragmentCardbookBinding>(
    R.layout.fragment_cardbook
) {

    private val viewModel : CardbookViewModel by viewModel()

    private val sharedViewModel : MainViewModel by sharedViewModel()

    private val adapter : CardbookAdapter by inject()

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