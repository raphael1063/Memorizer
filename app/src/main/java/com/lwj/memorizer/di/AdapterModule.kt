package com.lwj.memorizer.di

import com.lwj.memorizer.ui.cardbook.CardbookAdapter
import org.koin.dsl.module

val adapterModule = module {
    single { CardbookAdapter(get()) }
}