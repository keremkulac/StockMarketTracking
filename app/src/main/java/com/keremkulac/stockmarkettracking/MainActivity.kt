package com.keremkulac.stockmarkettracking

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.keremkulac.stockmarkettracking.ui.theme.StockMarketTrackingTheme
import com.keremkulac.stockmarkettracking.ui.view.stockMarketScreen.StockMarketScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StockMarketTrackingTheme {
                val navController = rememberNavController()
                StockMarketScreen(navController = navController)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    StockMarketTrackingTheme {
    }
}