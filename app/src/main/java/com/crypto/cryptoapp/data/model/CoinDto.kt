package com.crypto.cryptoapp.data.model

import com.crypto.cryptoapp.domain.model.Coin
import com.squareup.moshi.Json
data class CoinDto(
    val id: String,
    val symbol: String,
    val name: String,
    val image: String,
    @Json(name = "current_price") val price: Double,
    @Json(name = "price_change_percentage_24h") val change24h: Double?
)
fun CoinDto.toCoin() = Coin(
    id = id,
    symbol = symbol.uppercase(),
    name = name,
    image = image,
    price = price,
    change24h = change24h
)
