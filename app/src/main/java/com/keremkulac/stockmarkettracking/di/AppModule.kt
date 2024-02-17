package com.keremkulac.stockmarkettracking.di

import com.keremkulac.stockmarkettracking.repository.StockMarketRepository
import com.keremkulac.stockmarkettracking.service.StockMarketAPI
import com.keremkulac.stockmarkettracking.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideStockMarketRepository(
        stockMarketRepository: StockMarketAPI
    ) = StockMarketRepository(stockMarketRepository)

    @Singleton
    @Provides
    fun provideStockMarketApi() : StockMarketAPI{
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(StockMarketAPI::class.java)
    }
}