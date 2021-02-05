package com.lwj.memorizer.ui.cardbook

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lwj.memorizer.R
import com.lwj.memorizer.data.entities.Cardbook
import com.lwj.memorizer.databinding.ItemCardbookBinding

class CardbookAdapter(private val viewModel: CardbookViewModel) :
    ListAdapter<Cardbook, CardbookAdapter.CardbookViewHolder>(CARDBOOK_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardbookViewHolder {
        return CardbookViewHolder(
            DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_cardbook, parent, false
        ))
    }

    override fun onBindViewHolder(holder: CardbookViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CardbookViewHolder(private val binding: ItemCardbookBinding) :
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
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: Cardbook, newItem: Cardbook): Boolean {
                return oldItem == newItem
            }
        }
    }
}