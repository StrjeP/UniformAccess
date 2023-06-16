package org.uniform.idioms.a_store_compute

import org.uniform.demo.SimpleConsumerAveragingEventSource
import org.uniform.demo.XnYnC
import org.uniform.demo.model.Boundary
import org.uniform.demo.model.BoundedRequest
import org.uniform.demo.model.Event
import org.uniform.demo.model.EventConsumer

class StoreComputeFromComputeDemo : EventConsumer<XnYnC> {

    fun onData(d: XnYnC) {
        println(d)
    }

    fun onBoundary(b: Boundary) {
        println(b)
    }

    override fun onEvent(dataEvent: Event<XnYnC>) {
        dataEvent.onData { d -> onData(d) }
        dataEvent.onBoundary { b -> onBoundary(b) }
    }

    fun run() {
        val s = SimpleConsumerAveragingEventSource()
        s.request(BoundedRequest(0.0, 10.0), this)
    }

    companion object {
        @JvmStatic fun main(args : Array<String>) {
            val demoConsumer = StoreComputeFromComputeDemo()
            demoConsumer.run()
        }
    }
}