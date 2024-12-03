package com.local.spending_tracker.domain

interface CoreRepository {
    suspend fun updateBalance(balance: Double)
    suspend fun getBalance(): Double
}