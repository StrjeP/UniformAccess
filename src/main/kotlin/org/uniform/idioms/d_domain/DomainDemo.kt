package org.uniform.idioms.d_domain

import org.uniform.demo.SimpleDataEventSource
import org.uniform.demo.TransformingDataEventSource
import org.uniform.demo.XnY
import org.uniform.demo.model.*
import java.text.DecimalFormat

class DomainDemo : EventConsumer<String> {

    fun onData(d: String) {
        println(d)
    }

    fun onBoundary(b: Boundary) {
        println(b)
    }

    override fun onEvent(dataEvent: Event<String>) {
        dataEvent.onData { d -> onData(d) }
        dataEvent.onBoundary { b -> onBoundary(b) }
    }

    fun run() {
        val s = TransformingDataEventSource(SimpleDataEventSource())
        val request = TransformRequest<XnY, String>(BoundedRequest(1.3, 15.2)) { i -> "at ${i.x } the value is ${DecimalFormat("0.000").format(i.y)}" }
        s.request(request, this)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val demoConsumer = DomainDemo()
            demoConsumer.run()
        }
    }
}