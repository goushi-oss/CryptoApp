package com.crypto.cryptoapp
import com.crypto.cryptoapp.data.api.CoinGeckoApi
import com.crypto.cryptoapp.data.repositoryimpl.CoinRepositoryImpl
import com.crypto.cryptoapp.domain.usecase.GetCoinsUseCase
import com.crypto.cryptoapp.ui.list.CoinListState
import com.crypto.cryptoapp.ui.list.CoinListViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@OptIn(ExperimentalCoroutinesApi::class)
class CoinListViewModelTest {
    @get:Rule
    private lateinit var mockWebServer: MockWebServer
    private lateinit var api: CoinGeckoApi
    private lateinit var viewModel: CoinListViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        api = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(CoinGeckoApi::class.java)

        viewModel = CoinListViewModel(GetCoinsUseCase(CoinRepositoryImpl(api)))
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `loadCoins emits Success state when API returns data`() = runTest {
        // Given
        val mockResponse = """
            [
                {
                    "id": "bitcoin",
                    "symbol": "btc",
                    "name": "Bitcoin",
                    "image": "https://example.com/btc.png",
                    "current_price": 50000,
                    "price_change_percentage_24h": 2.5
                },
                {
                    "id": "ethereum",
                    "symbol": "eth",
                    "name": "Ethereum",
                    "image": "https://example.com/eth.png",
                    "current_price": 4000,
                    "price_change_percentage_24h": -1.2
                }
            ]
        """.trimIndent()

        mockWebServer.enqueue(MockResponse().setBody(mockResponse).setResponseCode(200))

        // When
        viewModel.loadCoins()
        advanceUntilIdle() // wait for coroutines

        // Then
        val state = viewModel.state.value
        assertTrue(state is CoinListState.Success)
        val coins = (state as CoinListState.Success).coins
        assert(coins.size == 2)
        assert(coins[0].id == "bitcoin")
        assert(coins[1].change24h!! < 0) // Ethereum negative change
    }
}
