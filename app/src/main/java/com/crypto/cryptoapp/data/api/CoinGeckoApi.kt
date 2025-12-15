package com.crypto.cryptoapp.data.api


import com.crypto.cryptoapp.data.model.CoinDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinGeckoApi {
    @GET("coins/markets")
    suspend fun getCoins(
        @Query("vs_currency") vsCurrency: String = "usd",
        @Query("category") category: String = "layer-1",
        @Query("price_change_percentage") change: String = "24h",
        @Query("x-cg-demo-api-key") apiKey: String = Constants.API_KEY
    ): List<CoinDto>
}
