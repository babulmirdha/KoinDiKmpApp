package com.babulmirdha.shared

class HelloRepository(private val helloService: HelloService) {
    fun greet() = helloService.sayHello()
}