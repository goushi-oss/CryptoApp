package com.crypto.cryptoapp.ui.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.crypto.cryptoapp.data.network.RetrofitClient
import com.crypto.cryptoapp.data.repositoryimpl.CoinRepositoryImpl
import com.crypto.cryptoapp.domain.usecase.GetCoinsUseCase

@Composable
fun CoinListScreen(
    onCoinClick: (String) -> Unit
) {
    // Using Singleton Retrofit
    val viewModel = remember {
        CoinListViewModel(
            GetCoinsUseCase(
                CoinRepositoryImpl(RetrofitClient.coinGeckoApi)
            )
        )
    }

    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadCoins()
    }

    when (state) {
        is CoinListState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is CoinListState.Error -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text((state as CoinListState.Error).message)
            }
        }

        is CoinListState.Success -> {
            val coins = (state as CoinListState.Success).coins
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(coins) { coin ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onCoinClick(coin.id) }
                            .padding(16.dp)
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(coin.image),
                            contentDescription = coin.name,
                            modifier = Modifier.size(40.dp)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text(text = coin.symbol, style = MaterialTheme.typography.titleMedium)
                            Text(text = coin.name)
                            Text(text = "$${coin.price}")

                            Text(
                                text = if ((coin.change24h ?: 0.0) >= 0)
                                    "↑ ${coin.change24h}%"
                                else
                                    "↓ ${coin.change24h}%",
                                color = if ((coin.change24h ?: 0.0) >= 0)
                                    MaterialTheme.colorScheme.primary
                                else
                                    MaterialTheme.colorScheme.error
                            )
                        }
                    }
                }
            }
        }
    }
}
