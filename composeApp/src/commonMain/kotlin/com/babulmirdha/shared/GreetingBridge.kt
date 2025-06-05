package com.babulmirdha.shared

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GreetingBridge : KoinComponent {
    private val useCase: GreetingUseCase by inject()

    fun greet(): String = useCase.greet()
}
