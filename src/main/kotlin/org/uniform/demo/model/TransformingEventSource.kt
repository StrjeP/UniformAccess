package org.uniform.demo.model

//class TransformingEventSource<S, T>(private val underlying: DataEventSource<S>) : DataEventSource<T>, EventConsumer<S> {
//    private lateinit var inbound: Transformer<T, Any>
//    private lateinit var outbound: Transformer<Any, T>
//    private lateinit var consumer: EventConsumer<T>
//    override fun request(request: Request, consumer: EventConsumer<T>) {
//        underlying.request(null, this as EventConsumer<S>)
//    }
//
//    override fun onEvent(dataEvent: Event<S>) {
//        dataEvent.onData { d -> consumer.onEvent(Event.data(outbound.transform(d))) }
//    }
//}