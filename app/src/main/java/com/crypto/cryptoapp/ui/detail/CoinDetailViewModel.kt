package com.crypto.cryptoapp.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crypto.cryptoapp.domain.model.Coin
import com.crypto.cryptoapp.domain.usecase.GetCoinDetailUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class CoinDetailState {
    object Loading : CoinDetailState()
    data class Success(val coin: Coin) : CoinDetailState()
    data class Error(val message: String) : CoinDetailState()
}

class CoinDetailViewModel(
    private val getCoinDetailUseCase: GetCoinDetailUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<CoinDetailState>(CoinDetailState.Loading)
    val state: StateFlow<CoinDetailState> = _state

    fun loadCoin(id: String) {
        viewModelScope.launch {
            _state.value = CoinDetailState.Loading
            try {
                _state.value = CoinDetailState.Success(getCoinDetailUseCase(id))
            } catch (e: Exception) {
                _state.value = CoinDetailState.Error(
                    e.message ?: "Error loading coin details"
                )
            }
        }
    }
}
