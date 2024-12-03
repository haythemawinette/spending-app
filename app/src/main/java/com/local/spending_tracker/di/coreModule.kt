package com.local.spending_tracker.di

import android.content.Context
import androidx.room.Room
import com.local.spending_tracker.data.Repositories.RoomSpendingDataSource
import com.local.spending_tracker.data.local.CoreRepositoryImpl
import com.local.spending_tracker.data.local.SpendingDatabase
import com.local.spending_tracker.domain.CoreRepository
import com.local.spending_tracker.domain.LocalSpendingDataSource
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module


val coreModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            SpendingDatabase::class.java,
            "spending_database_db"
        ).build()
    }

    single { get<SpendingDatabase>().dao }

    single {
        androidApplication().getSharedPreferences(
            "spending_tracker_preferences", Context.MODE_PRIVATE
        )
    }

    singleOf(::RoomSpendingDataSource).bind<LocalSpendingDataSource>()
    singleOf(::CoreRepositoryImpl).bind<CoreRepository>()
}