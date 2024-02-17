package com.keremkulac.stockmarkettracking.ui.view.stockMarketScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.keremkulac.stockmarkettracking.model.StockMarket

@Composable
fun StockMarketScreen(navController : NavController,viewModel: StockMarketViewModel = hiltViewModel()){
    StockMarketList(viewModel = viewModel)
}


@Composable
fun StockMarketRow( stockMarket: StockMarket){
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
        .background(color = Color.LightGray)
    ) {
        Row {
            Column(modifier = Modifier.weight(1f).padding(16.dp)) {
                Text(text = stockMarket.kod, fontSize = 20.sp)
                Text(text = stockMarket.ad, fontSize = 16.sp)
            }
            OutlinedButton(modifier = Modifier.align(alignment = CenterVertically),onClick = { /*TODO*/ }) {
                Text(text = "Daha fazla")
            }
        }
    }
}

@Composable
fun StockMarketListView(marketList: List<StockMarket>) {
    LazyColumn(modifier = Modifier.padding(vertical = 8.dp, horizontal = 8.dp)){
        items(marketList){stockMarketItem->
            StockMarketRow(stockMarket = stockMarketItem)
        }
    }
}

@Composable
fun StockMarketList(viewModel: StockMarketViewModel){
    val stockMarketList = viewModel.stockMarketList.value
    val errorMessage = viewModel.errorMessage
    val isLoading = viewModel.isLoading
    StockMarketListView(marketList = stockMarketList)
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        if(isLoading.value) {
            CircularProgressIndicator(color = MaterialTheme.colors.primary)
        }
        if(errorMessage.value.isNotEmpty()) {
            RetryView(error = errorMessage.value) {
                viewModel.loadStockMarkets()
            }
        }
    }
}

@Composable
fun RetryView(
    error: String,
    onRetry: () -> Unit
) {
    Column {
        Text(error, color = Color.Red, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = { onRetry() },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Retry")
        }
    }
}

