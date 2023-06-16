package org.uniform.demo

import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.uniform.demo.model.*

class SimpleAveragedDataEventSource : DataEventSource<XnYnC> {
    private lateinit var consumer: EventConsumer<XnYnC>
    private val sleepDuration = 0L
    override fun request(request: Request, consumer: EventConsumer<XnYnC>) {
        val rows = csvReader().readAll(sinewave).map { row -> XnYnC(row[0].toDouble(), row[1].toDouble(), row[2].toInt()) }

        this.consumer = consumer

        val (start, end) = when (request) {
            is BoundedRequest -> Pair(request.start, request.end)
            is UnBoundedRequest -> Pair(request.start, rows.last().x)
            else -> Pair(0.0, rows.last().x)
        }

        val job = GlobalScope.launch {
            var counter = 0
            while (counter < rows.size) {
                val record = rows[counter++]
                if (record.x in start..end) {
                    consumer?.let {
                        consumer.onEvent(Event.data(record))
                    }
                    delay(sleepDuration)
                }
            }
//            println("All data sent")
            consumer.onEvent(Event.boundary<XnYnC>(EndDataBoundary(end)))
        }
        runBlocking {
            job.join()
        }
    }
}

private val sinewave = """
1.0,0.4358263462,5
2.0,0.95084909625,4
3.0,0.520443675125,8
4.0,-0.3011505385,6
5.0,-0.9590813975,4
6.0,-0.5878680202500001,4
7.0,0.24459221439999998,5
8.0,0.8921212624285715,7
9.0,0.802840903,3
10.0,-0.038696672833333334,6
11.0,-0.8422073372,5
12.0,-0.8394532227999999,5
13.0,0.008189901800000005,5
14.0,0.7367190838571428,7
15.0,0.9135398585,6
16.0,0.20364332675,4
17.0,-0.7087156232,10
18.0,-0.963710844,4
19.0,-0.2948815981666667,6
20.0,0.5513729211666667,6
21.0,0.9857888165,4
22.0,0.51101642875,4
23.0,-0.301296236,4
24.0,-0.934022971,9
25.0,-0.5951917085,2
26.0,0.24030269214285713,7
27.0,0.9156674351999999,5
28.0,0.8274913198,5
29.0,-0.158940251,5
30.0,-0.8614795233999999,5
31.0,-0.7511970846,5
32.0,0.09547544975,4
33.0,0.8718057894285716,7
34.0,0.8851135389999999,3
35.0,0.0850296702,5
36.0,-0.805272918,5
37.0,-0.9155064681428573,7
""".trimIndent()
