package com.keremkulac.stockmarkettracking.ui.view.stockMarketScreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keremkulac.stockmarkettracking.model.StockMarket
import com.keremkulac.stockmarkettracking.repository.StockMarketRepository
import com.keremkulac.stockmarkettracking.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StockMarketViewModel @Inject constructor(
    private val stockMarketRepository: StockMarketRepository) : ViewModel(){

    var stockMarketList = mutableStateOf<List<StockMarket>>(listOf())
    var errorMessage = mutableStateOf("")
    var isLoading = mutableStateOf(false)

    init {
        loadStockMarkets()
    }

    fun loadStockMarkets(){
        viewModelScope.launch {
            isLoading.value = true
            when(val result = stockMarketRepository.getStockMarketList()){
                is Resource.Success -> {
                    val stockMarketItems = result.data!!.stockMarketItem
                    stockMarketList.value = stockMarketItems
                    errorMessage.value = ""
                    isLoading.value = false

                }

                is Resource.Error -> {
                    errorMessage.value = result.message!!
                    isLoading.value = false
                }

                is Resource.Loading -> {
                    isLoading.value = true
                }
            }
        }

    }
}