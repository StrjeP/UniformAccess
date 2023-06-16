package org.uniform

import org.uniform.demo.SimpleDataEventSource
import org.uniform.demo.XnY
import org.uniform.demo.model.Boundary
import org.uniform.demo.model.BoundedRequest
import org.uniform.demo.model.Event
import org.uniform.demo.model.EventConsumer

class DemoConsumer : EventConsumer<XnY> {

    fun onData(d: XnY) {
        println(d)
    }

    fun onBoundary(b: Boundary) {
        println(b)
    }

    override fun onEvent(dataEvent: Event<XnY>) {
        dataEvent.onData { d -> onData(d) }
        dataEvent.onBoundary { b -> onBoundary(b) }
    }

    fun run() {
        val s = SimpleDataEventSource()
        s.request(BoundedRequest(0.0, 10.0), this)
    }

    companion object {
        @JvmStatic fun main(args : Array<String>) {
            val demoConsumer = DemoConsumer()
            demoConsumer.run()
        }
    }
}