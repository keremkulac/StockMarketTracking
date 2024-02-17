package com.keremkulac.stockmarkettracking.repository

import com.keremkulac.stockmarkettracking.model.StockMarketList
import com.keremkulac.stockmarkettracking.service.StockMarketAPI
import com.keremkulac.stockmarkettracking.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class StockMarketRepository @Inject constructor(private val stockMarketAPI: StockMarketAPI){
    suspend fun getStockMarketList() : Resource<StockMarketList>{
        val response = try {
            stockMarketAPI.getStockMarketList()
        }catch (e : Exception){
            return Resource.Error("Veriler indirilemedi")
        }
        return Resource.Success(response)
    }

}