package com.local.spending_tracker.ui.spending_overview


sealed interface SpendingOverviewAction {
    data object LoadSpendingOverviewAndBalance: SpendingOverviewAction
    data class OnDateChange(val newDate: Int): SpendingOverviewAction
    data class OnDeleteSpending(val spendingId: Int): SpendingOverviewAction
}