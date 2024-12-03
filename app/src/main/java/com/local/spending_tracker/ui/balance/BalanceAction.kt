package com.local.spending_tracker.ui.balance


sealed interface BalanceAction {
    data class OnBalanceChanged(
        val newBalance: Double
    ) : BalanceAction

    data object OnBalanceSaved : BalanceAction
}