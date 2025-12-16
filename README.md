# Crypto Coins App (Jetpack Compose)

A clean, production-ready **Crypto Coins Android application** built using **Jetpack Compose**, **Clean Architecture**, and modern Android development best practices.

This project demonstrates strong skills in **architecture design**, **networking**, **state management**, and **UI development**.

---

## Objective

Build a modern Android application that:
- Fetches cryptocurrency data from CoinGecko API
- Displays a list of crypto coins
- Shows detailed information for a selected coin
- Uses scalable and testable architecture

---

## Tech Stack

| Category | Technology |
|------|---------|
| Language | Kotlin 2.2.10 |
| UI | Jetpack Compose (BOM 2025.08.00+) |
| Architecture | Clean Architecture + MVVM |
| Networking | Retrofit + Moshi |
| Image Loading | Coil |
| Async | Kotlin Coroutines + Flow |
| Min SDK | API 35 |
| Build Tool | Android Studio Narwhal 3 |
| Dependency Injection | Manual (Hilt ready) |
| Testing | JUnit, Coroutines Test (Optional) |

---

## Architecture Overview

The project follows **Clean Architecture**, divided into three layers:


### Why Clean Architecture?
- Separation of concerns
- Easy to test
- Scalable for large apps
- Independent of UI & framework

---

##  Features

### 1️ Crypto Coins List Screen
- Vertical list of cryptocurrencies
- Each item shows:
  - Coin logo (from URL)
  - Symbol (uppercase)
  - Coin name
  - Current price (USD)
  - 24h price change (↑ green / ↓ red)
- Pull-to-refresh support
- Loading & error states
- Tap to navigate to detail screen

### 2️Crypto Coin Detail Screen
- Large coin image
- Coin name & symbol
- Current price
- 24h percentage change
- Clean, minimal UI
- Graceful loading & error handling

---

##  API Integration

### Coin List API

Query Parameters:
- `vs_currency=usd`
- `category=layer-1`
- `price_change_percentage=1h`
- `x-cg-demo-api-key=YOUR_API_KEY`

---

### Coin Detail API


---

##  Testing 

- ViewModel unit tests using:
  - `kotlinx-coroutines-test`
- API mocking with:
  - `MockWebServer`
- State validation:
  - Loading → Success → Error

---

## UI & Accessibility

- Full Light & Dark mode support
- Material 3 design
- Accessible content descriptions for images
- Ready for TalkBack support

---

##  API Key

The API key is stored inside:
