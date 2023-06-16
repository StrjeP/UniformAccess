package org.uniform.demo

import org.uniform.demo.model.*

class SimpleConsumerAveragingEventSource : EventConsumer<XnY>, DataEventSource<XnYnC>{
    private lateinit var consumer: EventConsumer<XnYnC>

    var inscopeValues = mutableListOf<XnY>()
    fun onData(d: XnY) {
        if (inscopeValues.isEmpty()) {
            inscopeValues = mutableListOf(d)
        } else {
            val storedSecond = inscopeValues.first().x.toInt()
            val newValueSecond = d.x.toInt()
            if( storedSecond == newValueSecond ) {
                inscopeValues.add(d)
            } else {
                val average = XnYnC(storedSecond + 1.0,
                    inscopeValues.sumOf{it.y}/inscopeValues.size,
                    inscopeValues.size)
                consumer.onEvent(Event.data(average))
                println("${average.x},${average.y},${average.c}")
                inscopeValues = mutableListOf(d)
            }
         }
    }

    fun onBoundary(b: Boundary) {
        val storedSecond = inscopeValues.first().x.toInt()
        val average = XnYnC(storedSecond + 1.0, inscopeValues.sumOf{it.y}/inscopeValues.size, inscopeValues.size)
//        println("${average.x},${average.y}")
        consumer.onEvent(Event.data(average))
        consumer.onEvent(Event.BoundaryEvent(b))
    }

    override fun onEvent(dataEvent: Event<XnY>) {
        dataEvent.onData { d -> onData(d) }
        dataEvent.onBoundary { b -> onBoundary(b) }
    }

    override fun request(request: Request, consumer: EventConsumer<XnYnC>) {
        this.consumer = consumer
        val s = SimpleDataEventSource()
        s.request(request, this)
    }
}