package com.crypto.cryptoapp.domain.repository

import com.crypto.cryptoapp.domain.model.Coin

interface CoinRepository {
    suspend fun getCoins(): List<Coin>
}
