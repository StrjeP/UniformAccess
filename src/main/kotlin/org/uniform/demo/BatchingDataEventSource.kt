package org.uniform.demo

import org.uniform.demo.model.DataEventSource
import org.uniform.demo.model.Event
import org.uniform.demo.model.EventConsumer
import org.uniform.demo.model.Request

class BatchingDataEventSource(val underlying: DataEventSource<XnY>) : EventConsumer<XnY>, DataEventSource<Batch<XnY>> {
    private lateinit var consumer: EventConsumer<Batch<XnY>>
    override fun request(request: Request, consumer: EventConsumer<Batch<XnY>>) {
        this.consumer = consumer
        this.underlying.request(request, this as EventConsumer<XnY>)

    }

    var buffer = mutableListOf<XnY>()

    override fun onEvent(dataEvent: Event<XnY>) {
        dataEvent.onData { data ->
            this.buffer.add(data)
            if (this.buffer.size == 5) {
                consumer.onEvent(Event.data(buffer.toList()))
                this.buffer = mutableListOf()
            }
        }
        dataEvent.onBoundary { b -> consumer.onEvent(Event.boundary<String>(b)) }
    }
}