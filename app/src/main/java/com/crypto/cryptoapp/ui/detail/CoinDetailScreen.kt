package com.crypto.cryptoapp.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.crypto.cryptoapp.data.network.RetrofitClient
import com.crypto.cryptoapp.data.repositoryimpl.CoinDetailsRepositoryImpl
import com.crypto.cryptoapp.domain.usecase.GetCoinDetailUseCase

@Composable
fun CoinDetailScreen(coinId: String) {

    val viewModel = remember {
        CoinDetailViewModel(
            GetCoinDetailUseCase(
                CoinDetailsRepositoryImpl(
                    RetrofitClient.coinDetailApi
                )
            )
        )
    }

    val state by viewModel.state.collectAsState()

    LaunchedEffect(coinId) {
        viewModel.loadCoin(coinId)
    }

    when (state) {
        is CoinDetailState.Loading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is CoinDetailState.Error -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text((state as CoinDetailState.Error).message)
            }
        }

        is CoinDetailState.Success -> {
            val coin = (state as CoinDetailState.Success).coin
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = rememberAsyncImagePainter(coin.image),
                    contentDescription = coin.name,
                    modifier = Modifier.size(80.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(coin.name, style = MaterialTheme.typography.headlineMedium)
                Text(coin.symbol, style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Price: $${coin.price}")
                Text(
                    text = "24h Change: ${coin.change24h}%",
                    color = if ((coin.change24h ?: 0.0) >= 0)
                        MaterialTheme.colorScheme.primary
                    else
                        MaterialTheme.colorScheme.error
                )
            }
        }
    }
}
