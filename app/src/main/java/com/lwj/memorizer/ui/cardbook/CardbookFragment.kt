package com.lwj.memorizer.ui.cardbook

import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.lwj.memorizer.R
import com.lwj.memorizer.base.BaseFragment
import com.lwj.memorizer.data.entities.CardbookListStatus
import com.lwj.memorizer.databinding.FragmentCardbookBinding
import com.lwj.memorizer.ext.gone
import com.lwj.memorizer.ext.slideDown
import com.lwj.memorizer.ext.slideUp
import com.lwj.memorizer.ext.visible
import com.lwj.memorizer.ui.main.MainViewModel

class CardbookFragment : BaseFragment<FragmentCardbookBinding>(
    R.layout.fragment_cardbook
) {

    private val viewModel by activityViewModels<CardbookViewModel>()

    private val sharedViewModel by activityViewModels<MainViewModel>()

    private val linearAdapter by lazy {
        CardbookAdapter(viewModel, CardbookListStatus.LINEAR).apply {
            setHasStableIds(true)
        }
    }

    private val gridAdapter by lazy {
        CardbookAdapter(viewModel, CardbookListStatus.GRID).apply {
            setHasStableIds(true)
        }
    }

    override fun start() {

    }

    override fun setBinding() {
        binding.apply {
            vm = viewModel
            svm = sharedViewModel
            rvCardbookList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            rvCardbookList.adapter = linearAdapter
       }
    }

    override fun onObserve() {
        with(viewModel) {
            cardbookList.observe(viewLifecycleOwner, { list ->
                linearAdapter.submitList(list)
            })
        }
        with(sharedViewModel) {
            isGridView.observe(viewLifecycleOwner, {
                if(it) {
                    binding.apply {
                        rvCardbookList.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                        rvCardbookList.adapter = gridAdapter
                    }
                } else {
                    binding.apply {
                        rvCardbookList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                        rvCardbookList.adapter = linearAdapter
                    }
                }
            })
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