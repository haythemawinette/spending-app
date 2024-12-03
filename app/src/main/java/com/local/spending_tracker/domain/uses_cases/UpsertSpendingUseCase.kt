package com.local.spending_tracker.domain.uses_cases

import com.local.spending_tracker.domain.LocalSpendingDataSource
import com.local.spending_tracker.domain.models.Spending


class UpsertSpendingUseCase(
    private val spendingDataSource: LocalSpendingDataSource
) {

    suspend operator fun invoke(spending: Spending): Boolean {

        if (spending.name.isBlank()) {
            return false
        }
        if (spending.price < 0) {
            return false
        }
        if (spending.kilograms < 0) {
            return false
        }
        if (spending.quantity < 0) {
            return false
        }

        spendingDataSource.upsertSpending(spending)
        return true
    }

}