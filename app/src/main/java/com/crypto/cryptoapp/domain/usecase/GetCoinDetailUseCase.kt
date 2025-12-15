package com.crypto.cryptoapp.domain.usecase

import com.crypto.cryptoapp.domain.model.Coin
import com.crypto.cryptoapp.domain.repository.CoinDetailsRepository

class GetCoinDetailUseCase(
    private val repository: CoinDetailsRepository
) {
    suspend operator fun invoke(id: String): Coin =
        repository.getCoinDetail(id)
}
