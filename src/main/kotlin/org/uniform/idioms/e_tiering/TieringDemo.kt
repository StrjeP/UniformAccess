package org.uniform.idioms.e_tiering

import org.uniform.demo.SimpleDataEventSource
import org.uniform.demo.StreamDataEventSource
import org.uniform.demo.TwoTierDataEventSource
import org.uniform.demo.XnY
import org.uniform.demo.model.Boundary
import org.uniform.demo.model.Event
import org.uniform.demo.model.EventConsumer
import org.uniform.demo.model.UnBoundedRequest

class TieringDemo : EventConsumer<XnY> {

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
        val s = TwoTierDataEventSource(SimpleDataEventSource(), StreamDataEventSource())
        s.request(UnBoundedRequest(20.0), this)
    }
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val demoConsumer = TieringDemo()
            demoConsumer.run()
        }
    }
}