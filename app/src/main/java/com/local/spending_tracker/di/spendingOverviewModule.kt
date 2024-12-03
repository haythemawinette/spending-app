package com.local.spending_tracker.di

import com.local.spending_tracker.ui.spending_overview.SpendingOverviewViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val spendingOverviewModule = module {
    viewModel { SpendingOverviewViewModel(get(), get()) }
}