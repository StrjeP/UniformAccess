package org.uniform.demo

import org.uniform.demo.model.DataEventSource
import org.uniform.demo.model.Event
import org.uniform.demo.model.EventConsumer
import org.uniform.demo.model.Request

class BatchingDataEventSource(private val underlying: DataEventSource<XnY>, private val bufferSize : Int? = 5) : EventConsumer<XnY>, DataEventSource<Batch<XnY>> {
    private lateinit var consumer: EventConsumer<Batch<XnY>>
    private var buffer = mutableListOf<XnY>()
    override fun request(request: Request, consumer: EventConsumer<Batch<XnY>>) {
        this.consumer = consumer
        this.underlying.request(request, this as EventConsumer<XnY>)

    }

    override fun onEvent(dataEvent: Event<XnY>) {
        dataEvent.onData { data ->
            this.buffer.add(data)
            if (this.buffer.size == this.bufferSize) {
                this.consumer.onEvent(Event.data(this.buffer.toList()))
                this.buffer = mutableListOf()
            }
        }
        dataEvent.onBoundary { b -> consumer.onEvent(Event.boundary<String>(b)) }
    }
}