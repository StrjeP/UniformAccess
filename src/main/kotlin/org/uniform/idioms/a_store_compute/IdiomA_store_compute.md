# Idiom A: Uniform Computation and Storage
So the idiom is pretty simple, demonstrated by any RDBMS which has tables or views, or say 
within Spark.

The examples are pretty simple, copy'n'paste (may revist to parameterize). Basically the 
client code is indentical, same request and the same callback, nothing in the consumer can 
see whether the source is from storage or from computation. In the examples this is by using
specific data sources, though as the idioms progress this will be shown to be simple to replace
with a uniform request.

BoundedRequest(0.0, 10.0)

The code is simple to show the idea, how the consumer is not coupled to the store or 
compute, this could further be extended to use a different store or different compute. This
decoupling to allow additional flexibility. In isolation it isn't too exciting.

### DemoConsumerFromStore

This just uses a SimpleConsumerAveragingEventSource(), within this it uses the 
SimpleDataEventSource to access all the values and calculate the average.

### DemoConsumerFromCalc

This creates a SimpleAveragedEventSource which returns all the values from storage.



