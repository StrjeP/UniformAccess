package org.uniform.demo.model

interface EventConsumer<T> {
    fun onEvent(dataEvent: Event<T>)
}
