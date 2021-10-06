package com.springpractice.demo.grpc

import example.greeter.GreeterGrpcKt
import example.greeter.HelloRequest
import example.greeter.HelloResponse

class GreeterService : GreeterGrpcKt.GreeterCoroutineImplBase() {
    override suspend fun hello(request: HelloRequest) = HelloResponse.newBuilder().setText("Hello ${request.name}").build()
}