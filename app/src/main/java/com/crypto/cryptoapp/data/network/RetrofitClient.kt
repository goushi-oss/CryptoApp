package com.crypto.cryptoapp.data.network

import com.crypto.cryptoapp.data.api.CoinDetailApi
import com.crypto.cryptoapp.data.api.CoinGeckoApi
import com.crypto.cryptoapp.data.api.Constants
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitClient {
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    val coinGeckoApi: CoinGeckoApi by lazy {
        retrofit.create(CoinGeckoApi::class.java)
    }

    val coinDetailApi: CoinDetailApi by lazy {
        retrofit.create(CoinDetailApi::class.java)
    }
}
