package com.crypto.cryptoapp.data.api

import com.crypto.cryptoapp.data.model.CoinDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinDetailApi {

    @GET("coins/markets")
    suspend fun getCoinDetail(
        @Query("vs_currency") vsCurrency: String = "aud",
        @Query("include_platform") includePlatform: Boolean = false,
        @Query("ids") ids: String,
        @Query("x-cg-demo-api-key") key: String = Constants.API_KEY
    ): List<CoinDto>
}
