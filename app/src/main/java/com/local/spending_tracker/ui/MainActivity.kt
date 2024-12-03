package com.local.spending_tracker.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.local.spending_tracker.ui.balance.BalanceScreenCore
import com.ag_apps.spending_tracker.ui.core.theme.SpendingTrackerTheme
import com.local.spending_tracker.ui.util.util.Background
import com.local.spending_tracker.ui.util.util.Screen
import com.local.spending_tracker.ui.spending_details.SpendingDetailsScreenCore
import com.local.spending_tracker.ui.spending_overview.SpendingOverviewScreenCore

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SpendingTrackerTheme {
                Navigation(modifier = Modifier.fillMaxSize())
            }
        }
    }

    @Composable
    fun Navigation(modifier: Modifier = Modifier) {
        val navController = rememberNavController()

        Background()

        NavHost(
            modifier = modifier,
            navController = navController,
            startDestination = Screen.SpendingOverview
        ) {

            composable<Screen.SpendingOverview> {
                SpendingOverviewScreenCore(
                    onBalanceClick = {
                        navController.navigate(Screen.Balance)
                    },
                    onAddSpendingClick = {
                        navController.navigate(Screen.SpendingDetails(-1))
                    }
                )
            }

            composable<Screen.SpendingDetails> {
                SpendingDetailsScreenCore(
                    onSaveSpending = {
                        navController.popBackStack()
                    }
                )
            }

            composable<Screen.Balance> {
                BalanceScreenCore(
                    onSaveClick = {
                        navController.popBackStack()
                    }
                )
            }

        }
    }

}
