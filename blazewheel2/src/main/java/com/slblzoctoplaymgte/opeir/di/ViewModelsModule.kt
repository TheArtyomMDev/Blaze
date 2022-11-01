package com.slblzoctoplaymgte.opeir.di

import com.slblzoctoplaymgte.opeir.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var viewModelsModule = module {
    viewModel {
        MainViewModel(get(), get(), get())
    }
}