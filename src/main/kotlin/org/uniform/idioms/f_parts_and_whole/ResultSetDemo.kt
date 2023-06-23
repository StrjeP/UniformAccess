package org.uniform.idioms.f_parts_and_whole

import org.uniform.demo.EventSourceBackedResultSet
import org.uniform.demo.SimpleDataEventSource
import org.uniform.demo.model.UnBoundedRequest

class ResultSetDemo {


    fun run() {
        val dataset = EventSourceBackedResultSet()
        val s = SimpleDataEventSource()
        s.request(UnBoundedRequest(20.0), dataset)

        while (dataset.next()) {
            var x = dataset.getDouble(1)
            var y = dataset.getDouble("y")
            println("$x : $y")
        }
        //dataset.close()
    }
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val demoConsumer = ResultSetDemo()
            demoConsumer.run()
        }
    }
}