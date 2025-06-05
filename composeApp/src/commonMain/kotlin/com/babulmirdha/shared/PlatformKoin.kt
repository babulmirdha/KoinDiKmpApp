package com.babulmirdha.shared

import com.babulmirdha.shared.di.sharedModule
import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(sharedModule)
    }
}
