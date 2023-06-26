package org.uniform.demo

import org.uniform.demo.model.*

class ResponseTransformingDataEventSource(private val underlying: DataEventSource<XnY>) : EventConsumer<XnY>, DataEventSource<String> {
    private lateinit var consumer: EventConsumer<String>
    private lateinit var transformer: (a: XnY) -> String
    private val sleepDuration = 0L
    override fun request(request: Request, consumer: EventConsumer<String>) {
        this.consumer = consumer

        var transformRequest = request as TransformRequest<XnY, String>
        this.transformer = transformRequest.transformer
        this.underlying.request(transformRequest.request, this as EventConsumer<XnY>)
    }

    override fun onEvent(dataEvent: Event<XnY>) {
        dataEvent.onData { data -> consumer.onEvent(Event.data(this.transformer(data))) }
        dataEvent.onBoundary { b -> consumer.onEvent(Event.boundary<String>(b)) }
    }
}