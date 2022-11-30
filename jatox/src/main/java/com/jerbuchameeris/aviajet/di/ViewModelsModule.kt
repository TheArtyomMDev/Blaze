package com.jerbuchameeris.aviajet.di

import com.jerbuchameeris.aviajet.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var viewModelsModule = module {
    viewModel {
        MainViewModel(get(), get())
    }
}