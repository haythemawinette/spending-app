package com.local.spending_tracker.ui.spending_details


data class SpendingDetailsState(
    val name: String = "",
    val price: Double = 0.0,
    val kilograms: Double = 0.0,
    val quantity: Double = 0.0,
)
