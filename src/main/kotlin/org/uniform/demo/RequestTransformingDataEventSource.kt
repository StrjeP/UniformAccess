package org.uniform.demo

import org.uniform.demo.model.*

class RequestTransformingDataEventSource(private val underlying: DataEventSource<XnY>) : DataEventSource<XnY> {
    private lateinit var transformer: (a: XnY) -> String
    override fun request(request: Request, consumer: EventConsumer<XnY>) {

        var parts = (request as SomeStringRequest).request.split(",")
        var transformedRequest = BoundedRequest(parts[0].toDouble(), parts[1].toDouble())
        this.underlying.request(transformedRequest, consumer)
    }
}