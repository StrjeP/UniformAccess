package org.uniform.demo

import org.uniform.demo.model.Event
import org.uniform.demo.model.EventConsumer
import java.lang.Thread.sleep
import java.sql.SQLException
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.atomic.AtomicBoolean

// NOTE: if this was real code, I'd use coroutine channels, but would change the layering..
// OnEvent callback data to channel, channel close on boundary, while next keeps consuming unit all done.
// That would demonstrate coroutines, not the idiom.
class EventSourceBackedResultSet() : EventConsumer<XnY>, NotImplementedResultSet() {
    private var completed = AtomicBoolean(false)
    private val schema: Map<String, Int> = mapOf("x" to 1, "y" to 2)
    private var buffer = LinkedBlockingQueue<List<Double>>()
    private lateinit var currentValue: List<Double>

    override fun next(): Boolean {
        while (this.buffer.isEmpty() || !this.completed.acquire) {
            sleep(10)
        }
        if (this.buffer.isNotEmpty()) {
            this.currentValue = this.buffer.take();
            return true;
        }
        return false;
    }

    override fun getString(columnIndex: Int): String {
        return this.currentValue[columnIndex - 1].toString()
    }

    override fun getString(columnLabel: String?): String {
        var columnIndex = schema[columnLabel?.lowercase()] ?: throw SQLException("Invalid Column Label")
        return getString(columnIndex)
    }

    override fun getDouble(columnIndex: Int): Double {
        return this.currentValue[columnIndex - 1]
    }

    override fun getDouble(columnLabel: String?): Double {
        var columnIndex = schema[columnLabel?.lowercase()] ?: throw SQLException("Invalid Column Label")
        return getDouble(columnIndex)
    }

    override fun onEvent(dataEvent: Event<XnY>) {
        dataEvent.onData { data -> this.buffer.put(listOf(data.x, data.y)) }
        dataEvent.onBoundary { _ -> completed.getAndSet(true) }
    }
}