package com.lwj.memorizer.ui.cardbook

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.SimpleItemAnimator
import com.lwj.memorizer.R
import com.lwj.memorizer.base.BaseFragment
import com.lwj.memorizer.data.entities.Cardbook
import com.lwj.memorizer.databinding.FragmentCardbookBinding
import com.lwj.memorizer.ext.gone
import com.lwj.memorizer.ext.slideDown
import com.lwj.memorizer.ext.slideUp
import com.lwj.memorizer.ext.visible
import com.lwj.memorizer.ui.main.MainViewModel
import javax.inject.Inject

class CardbookFragment : BaseFragment<FragmentCardbookBinding>(
    R.layout.fragment_cardbook
) {

    private val viewModel by activityViewModels<CardbookViewModel>()

    private val sharedViewModel by activityViewModels<MainViewModel>()

    private val adapter by lazy {
        CardbookAdapter(viewModel).apply {
            setHasStableIds(true)
        }
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