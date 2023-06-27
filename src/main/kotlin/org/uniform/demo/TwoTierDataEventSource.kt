package org.uniform.demo

import org.uniform.demo.model.*

class TwoTierDataEventSource(private val first: DataEventSource<XnY>, private val second: DataEventSource<XnY>) : EventConsumer<XnY>,
    DataEventSource<XnY> {
    private lateinit var consumer: EventConsumer<XnY>
    override fun request(request: Request, consumer: EventConsumer<XnY>) {
        this.consumer = consumer
        this.first.request(request, this)
    }

    override fun onEvent(dataEvent: Event<XnY>) {
        dataEvent.onData { data -> consumer.onEvent(Event.data((data))) }
        dataEvent.onBoundary { b ->
            when (b) {
                is EndDataBoundary -> consumer.onEvent(dataEvent)
                is EndDataStartFromBoundary -> {
                    second.request(UnBoundedRequest(b.startFrom), this)
                }
            }
        }
    }
}