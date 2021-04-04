package com.lwj.memorizer.ui.cardbooklist

import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.lwj.memorizer.R
import com.lwj.memorizer.base.BaseFragment
import com.lwj.memorizer.data.entities.ARG_CARDBOOK_KEY
import com.lwj.memorizer.databinding.FragCardbookListBinding
import com.lwj.memorizer.ext.*
import com.lwj.memorizer.ui.cardbook.CardbookActivity
import com.lwj.memorizer.ui.main.MainViewModel

class CardbookListFragment : BaseFragment<FragCardbookListBinding>(
    R.layout.frag_cardbook_list
) {

    private val viewModel by activityViewModels<CardbookListViewModel>()

    private val sharedViewModel by activityViewModels<MainViewModel>()

    private lateinit var layoutManager: StaggeredGridLayoutManager

    private val adapter by lazy { CardbookListAdapter(viewModel, layoutManager) }

    override fun start() {
    }

    override fun setBinding() {
        binding.apply {
            vm = viewModel
            svm = sharedViewModel
            layoutManager = StaggeredGridLayoutManager(1, GridLayoutManager.VERTICAL)
            rvCardbookList.layoutManager = layoutManager
            rvCardbookList.adapter = adapter
        }
    }

    override fun observe() {
        with(viewModel) {
            cardbook.observe(viewLifecycleOwner, { list ->
                adapter.submitList(list)
            })
            openCardbook.observe(viewLifecycleOwner, { event ->
                event.getContentIfNotHandled()?.let { key ->
                    openActivity(CardbookActivity::class.java) {
                        putLong(ARG_CARDBOOK_KEY, key)
                    }
                }
            })
        }
        with(sharedViewModel) {
            isGridView.observe(viewLifecycleOwner, {
                    binding.apply {
                        if (it) {
                            layoutManager.spanCount = 2
                        } else {
                            layoutManager.spanCount = 1
                        }
                        adapter.notifyItemRangeChanged(0, adapter.itemCount)
                    }

            })
            isSearchBarOpened.observe(viewLifecycleOwner, { isVisible ->
                if (isVisible) {
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