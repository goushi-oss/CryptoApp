package com.crypto.cryptoapp.domain.usecase

import com.crypto.cryptoapp.domain.model.Coin
import com.crypto.cryptoapp.domain.repository.CoinRepository

class GetCoinsUseCase(private val repository: CoinRepository) {
    suspend operator fun invoke(): List<Coin> = repository.getCoins()
}
