package com.example.czg_primerappconktm

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "CZG_PrimerAppConKTM",
    ) {
        App()
    }
}