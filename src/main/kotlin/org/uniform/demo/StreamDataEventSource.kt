package org.uniform.demo

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.uniform.demo.model.*
import kotlin.math.sin

class StreamDataEventSource : DataEventSource<XnY> {
    private lateinit var consumer: EventConsumer<XnY>
    private val sleepDuration = 0L
    private val streamTerminatorX = 1000.0
    override fun request(request: Request, consumer: EventConsumer<XnY>) {
        this.consumer = consumer

        val (start, end) = when (request) {
            is BoundedRequest -> Pair(request.start, request.end)
            is UnBoundedRequest -> Pair(request.start, streamTerminatorX)
            else -> Pair(0.0, streamTerminatorX)
        }

        val job = GlobalScope.launch {
            var lastX = start + (1..40).random() / 100.0
            while (lastX <= end) {
                consumer.onEvent(Event.data(XnY(lastX, sin(lastX))))
                delay(sleepDuration)
                lastX += (1..40).random() / 100.0
            }
            consumer.onEvent(Event.boundary<XnY>(EndDataBoundary(end)))
        }
        runBlocking {
            job.join()
        }
    }
}