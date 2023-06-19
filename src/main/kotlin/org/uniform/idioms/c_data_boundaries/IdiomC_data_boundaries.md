# Idiom C: Data Boundaries

May already have noticed the two request types, bounded and unbounded requests. 
The bounded has a start and and end, the unbounded has just a start.

Though the curious may have already seen the event sources have a boundary
as an event. This allows a source to communicate both progress and "exhaustion".
So for a data store (bounded dataset) when all the data has completed then can 
send a boundary event with a value to communicate the last value.



**TODO**
Write some code to create data boundaries from a stream.
Use these boundaries to sink to a store.

Then show archive boundary to take down one more tier.