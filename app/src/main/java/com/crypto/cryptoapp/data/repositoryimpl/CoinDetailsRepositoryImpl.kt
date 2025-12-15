package com.crypto.cryptoapp.data.repositoryimpl

import com.crypto.cryptoapp.data.api.CoinDetailApi
import com.crypto.cryptoapp.data.model.toCoin
import com.crypto.cryptoapp.domain.model.Coin
import com.crypto.cryptoapp.domain.repository.CoinDetailsRepository

class CoinDetailsRepositoryImpl(
    private val api: CoinDetailApi
) : CoinDetailsRepository {

    override suspend fun getCoinDetail(id: String): Coin {
        return api.getCoinDetail(ids = id)
            .first()
            .toCoin()
    }
}
