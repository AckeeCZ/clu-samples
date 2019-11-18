package cz.ackee.clu.architecture.di

import cz.ackee.clu.architecture.screens.ad.AdsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { AdsViewModel(get()) }
}