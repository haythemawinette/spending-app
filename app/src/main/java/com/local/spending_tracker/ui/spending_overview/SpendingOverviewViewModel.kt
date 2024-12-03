package com.local.spending_tracker.ui.spending_overview

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.local.spending_tracker.domain.CoreRepository
import com.local.spending_tracker.domain.LocalSpendingDataSource
import com.local.spending_tracker.domain.models.Spending
import com.local.spending_tracker.ui.spending_overview.util.randomColor
import kotlinx.coroutines.launch
import java.time.ZonedDateTime


class SpendingOverviewViewModel(
    private val spendingDataSource: LocalSpendingDataSource,
    private val coreRepository: CoreRepository
) : ViewModel() {

    var state by mutableStateOf(SpendingOverviewState())
        private set

    fun onAction(action: SpendingOverviewAction) {
        when (action) {
            SpendingOverviewAction.LoadSpendingOverviewAndBalance -> {
                loadSpendingListAndBalance()
            }

            is SpendingOverviewAction.OnDateChange -> {
                val newDate = state.dateList[action.newDate]
                viewModelScope.launch {
                    state = state.copy(
                        pickedDate = newDate,
                        spendingList = getSpendingListByDate(newDate)
                    )
                }
            }

            is SpendingOverviewAction.OnDeleteSpending -> {
                viewModelScope.launch {
                    spendingDataSource.deleteSpending(action.spendingId)
                    state = state.copy(
                        spendingList = getSpendingListByDate(state.pickedDate),
                        dateList = spendingDataSource.getAllDates(),
                        balance = coreRepository.getBalance() - spendingDataSource.getSpendBalance(),
                    )
                }
            }
        }
    }

    private fun loadSpendingListAndBalance() {
        viewModelScope.launch {
            println("loadSpendingListAndBalance: ${coreRepository.getBalance()}")
            val allDates = spendingDataSource.getAllDates()

            state = state.copy(
                spendingList = getSpendingListByDate(
                    allDates.lastOrNull() ?: ZonedDateTime.now()
                ),
                balance = coreRepository.getBalance() - spendingDataSource.getSpendBalance(),
                pickedDate = allDates.lastOrNull() ?: ZonedDateTime.now(),
                dateList = allDates.reversed()
            )


        }
    }

    private suspend fun getSpendingListByDate(date: ZonedDateTime): List<Spending> {
        return spendingDataSource
            .getSpendingsByDate(date)
            .reversed()
            .map { it.copy(color = randomColor()) }
    }

}