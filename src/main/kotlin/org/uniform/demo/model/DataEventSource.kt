package org.uniform.demo.model

interface DataEventSource<T> {
    fun request(request: Request, consumer: EventConsumer<T>)
}
