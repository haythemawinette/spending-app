package com.local.spending_tracker.ui.spending_overview.util

import java.time.ZonedDateTime




fun ZonedDateTime.formatDate(): String {
    return "$dayOfMonth-$monthValue-$year"
}