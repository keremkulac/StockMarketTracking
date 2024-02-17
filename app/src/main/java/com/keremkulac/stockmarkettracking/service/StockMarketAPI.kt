package com.keremkulac.stockmarkettracking.service

import com.keremkulac.stockmarkettracking.model.StockMarketList
import retrofit2.http.GET

interface StockMarketAPI {
    //https://bigpara.hurriyet.com.tr/api/v1/hisse/list
    @GET("hisse/list")
    suspend fun getStockMarketList() : StockMarketList

}