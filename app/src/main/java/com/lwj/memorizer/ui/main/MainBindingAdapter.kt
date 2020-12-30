package com.lwj.memorizer.ui.main

import androidx.databinding.BindingAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView


@BindingAdapter("bind:Listener")
fun setOnNavigationItemSelectedListener(bottomNavView: BottomNavigationView, viewModel: MainViewModel) {
    bottomNavView.setOnNavigationItemSelectedListener {
        val checked = it.setChecked(true)
        viewModel.setCurrentNavId(checked.itemId)
        true
    }
}

@BindingAdapter("bind:PageChangeCallback")
fun setOnPageChangeCallback(vp: ViewPager2, viewModel: MainViewModel) {
    vp.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            viewModel.setCurrentViewPagerPosition(position)
        }
    })
}