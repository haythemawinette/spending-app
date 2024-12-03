package com.local.spending_tracker.di

import com.local.spending_tracker.domain.uses_cases.UpsertSpendingUseCase
import com.local.spending_tracker.ui.spending_details.SpendingDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module



val spendingDetailsModule = module {
    single { UpsertSpendingUseCase(get()) }
    viewModel { SpendingDetailsViewModel(get()) }
}