package org.uniform.idioms.f_parts_and_whole

import org.uniform.demo.Batch
import org.uniform.demo.BatchingDataEventSource
import org.uniform.demo.StreamDataEventSource
import org.uniform.demo.XnY
import org.uniform.demo.model.Boundary
import org.uniform.demo.model.Event
import org.uniform.demo.model.EventConsumer
import org.uniform.demo.model.UnBoundedRequest

class MicroBatchDemo : EventConsumer<Batch<XnY>> {

    fun onData(d: Batch<XnY>) {
        println("${d.size}, ${d.first()} to ${d.last()}")
    }

    fun onBoundary(b: Boundary) {
        println(b)
    }

    override fun onEvent(dataEvent: Event<Batch<XnY>>) {
        dataEvent.onData { d -> onData(d) }
        dataEvent.onBoundary { b -> onBoundary(b) }
    }

    fun run() {
        val s = BatchingDataEventSource(StreamDataEventSource(), 6)
        s.request(UnBoundedRequest(20.0), this)
    }
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val demoConsumer = MicroBatchDemo()
            demoConsumer.run()
        }
    }
}