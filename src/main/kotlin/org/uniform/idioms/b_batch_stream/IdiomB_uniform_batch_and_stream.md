# Idiom B: Uniform Computation and Storage
So the idiom is again pretty simple, not only can a uniform access allow 
the decoupling of storage and computation, it also decouple from the source 
being batch or a stream. This may be better to say allow the access of 
bounded and unbounded data in the same way.

### DemoConsumerFromStream
This uses a StreamDataEventSource which creates a new value of the sine wave
at a random interval (between 0.1 to 0.4), this means it will not be identical
to the two stored values for the range they cover, but will continue onwards.


### DemoConsumerFromStore
Basically the same as Idiom A, just the layer below the averaging calculator.

