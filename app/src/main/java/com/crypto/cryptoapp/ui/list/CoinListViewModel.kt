package com.crypto.cryptoapp.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crypto.cryptoapp.domain.model.Coin
import com.crypto.cryptoapp.domain.usecase.GetCoinsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class CoinListState {
    object Loading : CoinListState()
    data class Success(val coins: List<Coin>) : CoinListState()
    data class Error(val message: String) : CoinListState()
}

class CoinListViewModel(private val getCoinsUseCase: GetCoinsUseCase) : ViewModel() {

    private val _state = MutableStateFlow<CoinListState>(CoinListState.Loading)
    val state: StateFlow<CoinListState> = _state

    fun loadCoins() {
        _state.value = CoinListState.Loading
        viewModelScope.launch {
            try {
                val coins = getCoinsUseCase()
                _state.value = CoinListState.Success(coins)
            } catch (e: Exception) {
                _state.value = CoinListState.Error(e.message ?: "Unknown Error")
            }
        }
    }
}
