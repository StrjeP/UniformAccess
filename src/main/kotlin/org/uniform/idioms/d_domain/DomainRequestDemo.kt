package org.uniform.idioms.d_domain

import org.uniform.demo.RequestTransformingDataEventSource
import org.uniform.demo.SimpleDataEventSource
import org.uniform.demo.XnY
import org.uniform.demo.model.Boundary
import org.uniform.demo.model.Event
import org.uniform.demo.model.EventConsumer
import org.uniform.demo.model.SomeStringRequest

class DomainRequestDemo : EventConsumer<XnY> {

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
        val s = RequestTransformingDataEventSource(SimpleDataEventSource())
        val request = SomeStringRequest("4.3, 22.2")
        s.request(request, this)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val demoConsumer = DomainRequestDemo()
            demoConsumer.run()
        }
    }
}