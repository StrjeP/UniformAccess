# Idiom F: Parts and Whole

MicroBatchDemo a really simple example to create a micro-batch from a stream.
This is just an example, which creates micro batches by size, would Nagel this in
real life.

ResultSetDemo to show how the uniform access can be wrapped up as a JDBC
ResultSet. Show that it isn't a difficult problem, the code could easily be 
improved.

`while (dataset.next) { .... do .... }`


**Note** This idiom is to show how the paradigms from stream to microbatch and batch are
not hard to transition. There would be need for some code to do the work, but if
careful enough the request would be able to prepare the shape for the response
with the stateful transformer doing the work.

A hint that some of the idioms can collapse to general forms.