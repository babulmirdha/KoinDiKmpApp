package com.babulmirdha.shared

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GreetingUseCase : KoinComponent {
    private val repo: HelloRepository by inject()
    fun greet(): String = repo.greet()
}
