package com.local.spending_tracker.ui.spending_details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.local.spending_tracker.domain.models.Spending
import com.local.spending_tracker.domain.uses_cases.UpsertSpendingUseCase
import com.local.spending_tracker.ui.spending_details.SpendingDetailsAction
import com.local.spending_tracker.ui.spending_details.SpendingDetailsEvent
import com.local.spending_tracker.ui.spending_details.SpendingDetailsState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.time.ZonedDateTime


class SpendingDetailsViewModel(
    private val upsertSpendingUseCase: UpsertSpendingUseCase
) : ViewModel() {

    var state by mutableStateOf(SpendingDetailsState())
        private set

    private val _eventChannel = Channel<SpendingDetailsEvent>()
    val event = _eventChannel.receiveAsFlow()

    fun onAction(action: SpendingDetailsAction) {
        when (action) {
            is SpendingDetailsAction.UpdateKilograms -> {
                state = state.copy(
                    kilograms = action.newKilograms
                )
            }

            is SpendingDetailsAction.UpdateName -> {
                state = state.copy(
                    name = action.newName
                )
            }

            is SpendingDetailsAction.UpdatePrice -> {
                state = state.copy(
                    price = action.newPrice
                )
            }

            is SpendingDetailsAction.UpdateQuantity -> {
                state = state.copy(
                    quantity = action.newQuantity
                )
            }

            SpendingDetailsAction.SaveSpending -> {
                viewModelScope.launch {
                    if (saveSpending()) {
                        _eventChannel.send(SpendingDetailsEvent.SaveSuccess)
                    } else {
                        _eventChannel.send(SpendingDetailsEvent.SaveFailed)
                    }
                }
            }
        }
    }

    private suspend fun saveSpending(): Boolean {
        val spending = Spending(
            spendingId = null,
            name = state.name,
            price = state.price,
            kilograms = state.kilograms,
            quantity = state.quantity,
            dateTimeUtc = ZonedDateTime.now()
        )

        return upsertSpendingUseCase(spending)
    }

}