package com.lwj.memorizer.ui.cardbooklist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.lwj.memorizer.R
import com.lwj.memorizer.data.entities.CardbookList
import com.lwj.memorizer.data.entities.GRID_TYPE
import com.lwj.memorizer.data.entities.LINEAR_TYPE
import com.lwj.memorizer.databinding.ItemCardbookListGridBinding
import com.lwj.memorizer.databinding.ItemCardbookListLinearBinding
import com.orhanobut.logger.Logger

class CardbookListAdapter(private val listViewModel: CardbookListViewModel, private val layoutManager: StaggeredGridLayoutManager) :
    ListAdapter<CardbookList, RecyclerView.ViewHolder>(CARDBOOK_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType == LINEAR_TYPE) {
            CardbookLinearViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_cardbook_list_linear, parent, false
                )
            )
        } else {
            CardbookGridViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_cardbook_list_grid, parent, false
                )
            )
        }
    }

    override fun getItemViewType(position: Int) = if(layoutManager.spanCount == 1) LINEAR_TYPE else GRID_TYPE

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is CardbookGridViewHolder -> {
                holder.bind(getItem(position))
            }
            is CardbookLinearViewHolder -> {
                holder.bind(getItem(position))
            }
        }
    }

    inner class CardbookGridViewHolder(private val binding: ItemCardbookListGridBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CardbookList) {
            binding.apply {
                vm = listViewModel
                model = item
                count = 0 //count of words in the cardbook
                position = adapterPosition
                executePendingBindings()
            }
        }
    }

    inner class CardbookLinearViewHolder(private val binding: ItemCardbookListLinearBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CardbookList) {
            binding.apply {
                vm = listViewModel
                model = item
                count = 0 //count of words in the cardbook
                position = adapterPosition
                executePendingBindings()
            }
        }
    }

    companion object {
        private val CARDBOOK_COMPARATOR = object : DiffUtil.ItemCallback<CardbookList>() {
            override fun areItemsTheSame(oldItem: CardbookList, newItem: CardbookList): Boolean {
                Logger.d(oldItem.idx)
                Logger.d(newItem.idx)
                return oldItem.idx == newItem.idx
            }

            override fun areContentsTheSame(oldItem: CardbookList, newItem: CardbookList): Boolean {
                return oldItem == newItem
            }
        }
    }
}