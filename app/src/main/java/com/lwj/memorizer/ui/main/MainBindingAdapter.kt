package com.lwj.memorizer.ui.main

import androidx.appcompat.widget.Toolbar
import androidx.databinding.BindingAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.lwj.memorizer.R


@BindingAdapter("bind:setOnNavigationItemSelectedListener")
fun setOnNavigationItemSelectedListener(bottomNavView: BottomNavigationView, viewModel: MainViewModel) {
    bottomNavView.setOnNavigationItemSelectedListener {
        val checked = it.setChecked(true)
        viewModel.setCurrentNavId(checked.itemId)
        true
    }
}

@BindingAdapter("bind:setOnPageChangeCallback")
fun setOnPageChangeCallback(vp: ViewPager2, viewModel: MainViewModel) {
    vp.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            viewModel.setCurrentViewPagerPosition(position)
        }
    })
}

@BindingAdapter("bind:setOnToolbarItemClickListener")
fun setOnToolbarItemClickListener(toolbar: Toolbar, viewModel: MainViewModel) {
    toolbar.setOnMenuItemClickListener {
        when (it.itemId) {
            R.id.toolbar_main_reorder -> viewModel.onReorderMenuClicked()
            R.id.toolbar_menu_cardbook_search -> viewModel.onSearchMenuClicked()
            R.id.toolbar_menu_add_cardbook -> viewModel.onAddCardbookClicked()
            R.id.toolbar_menu_settings -> viewModel.onSettingMenuClicked()
            R.id.toolbar_menu_support -> viewModel.onSupportMenuClicked()
            R.id.toolbar_menu_about -> viewModel.onAboutMenuClicked()
        }
        true
    }
}