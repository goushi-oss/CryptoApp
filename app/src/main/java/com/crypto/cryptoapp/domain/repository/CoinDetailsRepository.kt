package com.crypto.cryptoapp.domain.repository

import com.crypto.cryptoapp.domain.model.Coin

interface CoinDetailsRepository {
    suspend fun getCoinDetail(id: String): Coin
}
