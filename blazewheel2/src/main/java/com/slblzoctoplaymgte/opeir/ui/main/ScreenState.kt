package com.slblzoctoplaymgte.opeir.ui.main

sealed class ScreenState {
    object Playing: ScreenState()
    object Choosing: ScreenState()
    data class Loading(val progress: Float): ScreenState()
}
