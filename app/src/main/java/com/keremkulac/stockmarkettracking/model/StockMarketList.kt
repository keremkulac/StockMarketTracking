package com.keremkulac.stockmarkettracking.model

import com.google.gson.annotations.SerializedName

data class StockMarketList(
    val code : String,
    @SerializedName("data")
    val stockMarketItem : List<StockMarket>
)