package com.crypto.cryptoapp.domain.model

data class Coin(
    val id: String,
    val symbol: String,
    val name: String,
    val image: String,
    val price: Double,
    val change24h: Double?
)
