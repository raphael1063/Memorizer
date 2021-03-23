package com.lwj.memorizer.ui.cardbook

import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.lwj.memorizer.R
import com.lwj.memorizer.base.BaseFragment
import com.lwj.memorizer.data.entities.CardbookListStatus
import com.lwj.memorizer.data.entities.GRID_TYPE
import com.lwj.memorizer.databinding.FragCardbookBinding
import com.lwj.memorizer.ext.gone
import com.lwj.memorizer.ext.slideDown
import com.lwj.memorizer.ext.slideUp
import com.lwj.memorizer.ext.visible
import com.lwj.memorizer.ui.main.MainViewModel

class   CardbookFragment : BaseFragment<FragCardbookBinding>(
    R.layout.frag_cardbook
) {

    private val viewModel by activityViewModels<CardbookViewModel>()

    private val sharedViewModel by activityViewModels<MainViewModel>()

    private val adapter by lazy {
        CardbookAdapter(viewModel, CardbookListStatus.LINEAR).apply {
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
            rvCardbookList.adapter = adapter
       }
    }

    override fun observe() {
        with(viewModel) {
            cardbookList.observe(viewLifecycleOwner, { list ->
                adapter.submitList(list)
            })
        }
        with(sharedViewModel) {
            isGridView.observe(viewLifecycleOwner, { event ->
                event.getContentIfNotHandled()?.let {
                    if(it) {
                        binding.apply {
                            rvCardbookList.layoutManager = StaggeredGridLayoutManager(2, GridLayoutManager.VERTICAL)
                            adapter.changeLayoutManager(CardbookListStatus.GRID)
                        }
                    } else {
                        binding.apply {
                            rvCardbookList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                            adapter.changeLayoutManager(CardbookListStatus.LINEAR)
                        }
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