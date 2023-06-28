package com.example.obligor.ui.destinations

sealed class Destination(val route: String) {
    object HomeScreen : Destination("home_screen")
    object PromiserSelectionScreen : Destination("promiser_selection_screen")
}