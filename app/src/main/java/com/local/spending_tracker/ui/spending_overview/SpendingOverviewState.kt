package com.local.spending_tracker.ui.spending_overview

import com.local.spending_tracker.domain.models.Spending
import java.time.ZonedDateTime

data class SpendingOverviewState(
    val spendingList: List<Spending> = emptyList(),
    val dateList: List<ZonedDateTime> = emptyList(),
    val balance: Double = 0.0,
    val pickedDate: ZonedDateTime = ZonedDateTime.now(),
    val isDropDownMenuVisible: Boolean = false,
)
