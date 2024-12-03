package com.local.spending_tracker.domain.models

import java.time.ZonedDateTime


data class Spending(
    val spendingId: Int?,
    val name: String,
    val price: Double,
    val kilograms: Double,
    val quantity: Double,
    val dateTimeUtc: ZonedDateTime,
    val color : Int = 0
)
