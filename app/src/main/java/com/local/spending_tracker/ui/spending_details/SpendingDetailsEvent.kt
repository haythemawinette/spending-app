package com.local.spending_tracker.ui.spending_details


sealed interface SpendingDetailsEvent {

    data object SaveSuccess: SpendingDetailsEvent
    data object SaveFailed: SpendingDetailsEvent

}