package com.babulmirdha.shared.di

import com.babulmirdha.shared.HelloRepository
import com.babulmirdha.shared.HelloService
import com.babulmirdha.shared.HelloServiceImpl
import org.koin.dsl.module

val sharedModule = module {
    single<HelloService> { HelloServiceImpl() }
    single { HelloRepository(get()) }
}
