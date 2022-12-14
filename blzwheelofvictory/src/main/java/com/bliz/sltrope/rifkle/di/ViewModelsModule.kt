package com.bliz.sltrope.rifkle.di

import com.bliz.sltrope.rifkle.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var viewModelsModule = module {
    viewModel {
        MainViewModel(get(), get())
    }
}