package com.example.czg_primerappconktm

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform