package com.crypto.cryptoapp.data.repositoryimpl

import com.crypto.cryptoapp.data.api.CoinGeckoApi
import com.crypto.cryptoapp.domain.model.Coin
import com.crypto.cryptoapp.domain.repository.CoinRepository

class CoinRepositoryImpl(private val api: CoinGeckoApi) : CoinRepository {
    override suspend fun getCoins(): List<Coin> =
        api.getCoins().map {
            Coin(
                id = it.id,
                symbol = it.symbol.uppercase(),
                name = it.name,
                image = it.image,
                price = it.price,
                change24h = it.change24h
            )
        }


}
