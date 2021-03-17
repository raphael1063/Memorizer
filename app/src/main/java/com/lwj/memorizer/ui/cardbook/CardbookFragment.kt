package com.lwj.memorizer.ui.cardbook

import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.SimpleItemAnimator
import com.lwj.memorizer.R
import com.lwj.memorizer.base.BaseFragment
import com.lwj.memorizer.databinding.FragCardbookBinding
import com.lwj.memorizer.ext.gone
import com.lwj.memorizer.ext.slideDown
import com.lwj.memorizer.ext.slideUp
import com.lwj.memorizer.ext.visible
import com.lwj.memorizer.ui.main.MainViewModel

class CardbookFragment : BaseFragment<FragCardbookBinding>(
    R.layout.frag_cardbook
) {

    private val viewModel by activityViewModels<CardbookViewModel>()

    private val sharedViewModel by activityViewModels<MainViewModel>()

    private val adapter by lazy {
        CardbookAdapter(viewModel)
    }

    override fun start() {

    }

    override fun setBinding() {
        binding.apply {
            vm = viewModel
            svm = sharedViewModel
            rvCardbookList.adapter = adapter
            (rvCardbookList.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        }
    }

    override fun onObserve() {
        with(viewModel) {
            cardbookList.observe(viewLifecycleOwner, { list ->
                adapter.submitList(list)
            })
        }
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