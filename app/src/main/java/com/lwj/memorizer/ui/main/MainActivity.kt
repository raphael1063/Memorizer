package com.lwj.memorizer.ui.main

import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.lwj.memorizer.R
import com.lwj.memorizer.base.BaseActivity
import com.lwj.memorizer.databinding.ActivityMainBinding
import com.lwj.memorizer.ui.cardbook.CardBookFragment
import com.lwj.memorizer.ui.home.HomeFragment
import com.lwj.memorizer.ui.myaccount.MyAccountFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>(
    R.layout.activity_main
) {

    private val viewModel: MainViewModel by viewModel()

    override fun start() {
       setBinding()
    }

    private fun setBinding() {
        binding.apply {
            homeVp2.apply {
                adapter = PagerAdapter(supportFragmentManager, lifecycle)
                registerOnPageChangeCallback(PageChangeCallback())
            }
            homeBottomNavView.apply {
                setOnNavigationItemSelectedListener { navigationSelected(it) }
            }
        }
    }

    override fun onObserve() {
    }

    private inner class PagerAdapter(fm: FragmentManager, lifecycle: Lifecycle) :
            FragmentStateAdapter(fm, lifecycle) {
        override fun getItemCount() = 3

        override fun createFragment(position: Int): Fragment {
            return when(position) {
                0 -> HomeFragment()
                1 -> CardBookFragment()
                2 -> MyAccountFragment()
                else -> error("no such position: $position")
            }
        }
    }

    private fun navigationSelected(item: MenuItem): Boolean {
        val checked = item.setChecked(true)
        when (checked.itemId) {
            R.id.nav_home -> {
                binding.homeVp2.currentItem = 0
                return true
            }
            R.id.nav_cardbook -> {
                binding.homeVp2.currentItem = 1
                return true
            }
            R.id.nav_my_account -> {
                binding.homeVp2.currentItem = 2
                return true
            }
        }
        return false
    }

    private inner class PageChangeCallback: ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            home_bottom_nav_view.selectedItemId = when (position) {
                0 -> R.id.nav_home
                1 -> R.id.nav_cardbook
                2 -> R.id.nav_my_account
                else -> error("no such position: $position")
            }
        }
    }
}