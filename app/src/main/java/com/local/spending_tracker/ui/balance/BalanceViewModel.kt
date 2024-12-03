package com.local.spending_tracker.ui.balance

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.local.spending_tracker.domain.CoreRepository
import com.local.spending_tracker.ui.balance.BalanceAction
import com.local.spending_tracker.ui.balance.BalanceState
import kotlinx.coroutines.launch


class BalanceViewModel(
    private val coreRepository: CoreRepository
) : ViewModel() {

    var state by mutableStateOf(BalanceState())
        private set

    init {
        viewModelScope.launch {
            state = state.copy(
                balance = coreRepository.getBalance()
            )
        }
    }

    fun onAction(action: BalanceAction) {
        when (action) {
            is BalanceAction.OnBalanceChanged -> {
                state = state.copy(
                    balance = action.newBalance
                )
            }

            BalanceAction.OnBalanceSaved -> {
                viewModelScope.launch {
                    coreRepository.updateBalance(state.balance)
                }
            }
        }
    }

}