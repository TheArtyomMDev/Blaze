package com.jerbuchameeris.aviajet.ui.main

sealed class RequestState {
    object Loading: RequestState()
    data class Success(val url: String): RequestState()
    object Failed: RequestState()
}
