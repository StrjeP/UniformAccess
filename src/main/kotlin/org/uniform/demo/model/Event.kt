package org.uniform.demo.model

sealed class Event<out T> {
    companion object {
        fun <T> data(value: T): Event<T> = DataEvent(value)
        fun <T> boundary(value: Boundary): Event<Nothing> = BoundaryEvent(value)
    }
    data class DataEvent<out T> internal constructor(val value: T) : Event<T>() {
    }

    data class BoundaryEvent<out T> internal constructor(val value: Boundary) : Event<T>() {
    }

    fun isData(): Boolean {
        return this@Event is DataEvent<T>
    }

    fun isBoundary(): Boolean {
        return this@Event is BoundaryEvent
    }

    inline fun onData(action: (data: T) -> Unit): Event<T> {
        return also { if (it.isData()) action((it as DataEvent<T>).value) }
    }

    inline fun onBoundary(action: (boundary: Boundary) -> Unit): Event<T> {
        return also { if (it.isBoundary()) action((it as BoundaryEvent).value) }
    }
}
