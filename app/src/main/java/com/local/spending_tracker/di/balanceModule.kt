package com.local.spending_tracker.di

import com.local.spending_tracker.ui.balance.BalanceViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module



val balanceModule = module {
    viewModel { BalanceViewModel(get()) }
}