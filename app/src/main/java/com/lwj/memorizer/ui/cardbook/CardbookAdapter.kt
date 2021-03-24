package com.lwj.memorizer.ui.cardbook

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.lwj.memorizer.R
import com.lwj.memorizer.data.entities.Cardbook
import com.lwj.memorizer.data.entities.CardbookListStatus
import com.lwj.memorizer.data.entities.GRID_TYPE
import com.lwj.memorizer.data.entities.LINEAR_TYPE
import com.lwj.memorizer.databinding.ItemCardbookGridBinding
import com.lwj.memorizer.databinding.ItemCardbookLinearBinding
import com.orhanobut.logger.Logger

class CardbookAdapter(private val viewModel: CardbookViewModel, private val layoutManager: StaggeredGridLayoutManager) :
    ListAdapter<Cardbook, RecyclerView.ViewHolder>(CARDBOOK_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType == LINEAR_TYPE) {
            CardbookLinearViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_cardbook_linear, parent, false
                )
            )
        } else {
            CardbookGridViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_cardbook_grid, parent, false
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

    inner class CardbookGridViewHolder(private val binding: ItemCardbookGridBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Cardbook) {
            binding.apply {
                vm = viewModel
                model = item
                count = 0 //count of words in the cardbook
                position = adapterPosition
                executePendingBindings()
            }
        }
    }

    inner class CardbookLinearViewHolder(private val binding: ItemCardbookLinearBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Cardbook) {
            binding.apply {
                vm = viewModel
                model = item
                count = 0 //count of words in the cardbook
                position = adapterPosition
                executePendingBindings()
            }
        }
    }

    companion object {
        private val CARDBOOK_COMPARATOR = object : DiffUtil.ItemCallback<Cardbook>() {
            override fun areItemsTheSame(oldItem: Cardbook, newItem: Cardbook): Boolean {
                Logger.d(oldItem.idx)
                Logger.d(newItem.idx)
                return oldItem.idx == newItem.idx
            }

            override fun areContentsTheSame(oldItem: Cardbook, newItem: Cardbook): Boolean {
                return oldItem == newItem
            }
        }
    }
}