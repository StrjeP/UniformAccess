package org.uniform.idioms.i_locator

import org.uniform.demo.*
import org.uniform.demo.model.DataEventSource
import org.uniform.demo.model.Request
import org.uniform.demo.model.SimpleDataEventSourceRequest
import org.uniform.idioms.g_identity.SimpleIdentity

class SimpleLocator {

    private val sinestore =
        SimpleIdentity("realm/sine/store", "sinewave", mapOf<String, Any>("min" to 0.0, "max" to 37.66))
    private val sinestream =
        SimpleIdentity("realm/sine/stream", "sinewave", mapOf<String, Any>("min" to 37.67, "max" to 1000.0))
    private val tiered =
        SimpleIdentity("realm/sine/tiered", "sinewave", mapOf<String, Any>("min" to 0.0, "max" to 1000.0))
    private val avgsinestore =
        SimpleIdentity("realm/sine/avgstore", "averaged", mapOf<String, Any>("min" to 0.0, "max" to 37.0))

    private val registryTypeToIdentity = mapOf(
        sinestore to SimpleDataEventSource(),
        sinestream to StreamDataEventSource(),
        tiered to TwoTierDataEventSource(SimpleDataEventSource(), StreamDataEventSource()),
        avgsinestore to SimpleAveragedDataEventSource(),
    )

    fun request(request: Request, consumer: (x: DataEventSource<XnY>?) -> Void) {
        when (request) {

        }
        val theRequest = request as SimpleDataEventSourceRequest
        val candidates = registryTypeToIdentity.entries.filter { it.key.typeId == theRequest.type }

        val store = registryTypeToIdentity[tiered] as DataEventSource<XnY>
        consumer(store)
    }
}