package com.local.spending_tracker

import android.app.Application
import com.local.spending_tracker.di.balanceModule
import com.local.spending_tracker.di.coreModule
import com.local.spending_tracker.di.spendingDetailsModule
import com.local.spending_tracker.di.spendingOverviewModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                coreModule,
                balanceModule,
                spendingOverviewModule,
                spendingDetailsModule
            )
        }

    }

}