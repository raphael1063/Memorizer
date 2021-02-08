package com.lwj.memorizer.ui.main

import android.annotation.SuppressLint
import android.view.Menu
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.lwj.memorizer.R
import com.lwj.memorizer.base.BaseActivity
import com.lwj.memorizer.data.entities.CardbookListStatus
import com.lwj.memorizer.databinding.ActivityMainBinding
import com.lwj.memorizer.ext.snack
import com.lwj.memorizer.ui.cardbook.CardbookFragment
import com.lwj.memorizer.ui.common.PagerAdapter
import com.lwj.memorizer.ui.home.HomeFragment
import com.lwj.memorizer.ui.myaccount.MyAccountFragment
import com.lwj.memorizer.ui.training.TrainingFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(
    R.layout.activity_main
) {

    private val viewModel by viewModels<MainViewModel>()

    private val fragments = arrayListOf<Fragment>(
        HomeFragment(),
        CardbookFragment(),
        TrainingFragment(),
        MyAccountFragment()
    )

    override fun start() {
        setBinding()
    }

    private fun setBinding() {
        binding.apply {
            vm = viewModel
            lifecycleOwner = this@MainActivity
            vp2Main.adapter = PagerAdapter(supportFragmentManager, lifecycle, fragments)
            vp2Main.offscreenPageLimit = 4
            toolbarMain.menu.getItem(0)
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onObserve() {
        with(viewModel) {
            currentNavigationItem.observe(this@MainActivity, { resId ->
                binding.bnvMain.selectedItemId = resId
            })
            currentViewPagerItem.observe(this@MainActivity, { position ->
                binding.vp2Main.currentItem = position
            })
            isCardbookView.observe(this@MainActivity, { boolean ->
                binding.toolbarMain.menu[1].isVisible = boolean
                binding.toolbarMain.menu[2].isVisible = boolean
            })
            reorderIconStatus.observe(this@MainActivity, { status ->
                if(status == CardbookListStatus.GRID) {
                    binding.toolbarMain.menu[0].icon =
                        resources.getDrawable(R.drawable.ic_grid_view, theme)
                } else {
                    binding.toolbarMain.menu[0].icon =
                        resources.getDrawable(R.drawable.ic_list_view, theme)
                }
            })
            isSearchMenuActivate.observe(this@MainActivity, { isActivated ->
                if (isActivated) {
                    binding.toolbarMain.menu[1].icon =
                        resources.getDrawable(R.drawable.ic_search_active, theme)
                } else {
                    binding.toolbarMain.menu[1].icon =
                        resources.getDrawable(R.drawable.ic_search_inactive, theme)
                }
            })
            actionSettingButtonClicked.observe(this@MainActivity, { event ->
                event.getContentIfNotHandled()?.let {

                }
            })
            actionSupportButtonClicked.observe(this@MainActivity, { event ->
                event.getContentIfNotHandled()?.let {

                }
            })
            actionAboutButtonClicked.observe(this@MainActivity, { event ->
                event.getContentIfNotHandled()?.let {

                }
            })
        }
    }

    private var doubleBackToExitPressedOnce = false
    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }

        this.doubleBackToExitPressedOnce = true
        binding.root.snack(R.string.back_press_alert, Snackbar.LENGTH_SHORT) {
            setActionTextColor(ContextCompat.getColor(this@MainActivity, R.color.black))
        }

        CoroutineScope(Dispatchers.Main).launch {
            delay(2000).run {
                doubleBackToExitPressedOnce = false
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }
}