package com.babulmirdha.koin_kmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform