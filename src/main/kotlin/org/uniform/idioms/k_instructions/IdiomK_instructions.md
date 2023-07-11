# Idiom K: Instructions

This idiom is conceptual, instead of making different processing interfaces for 
eveerything, simple provide instructions.

For a consumer, instead of an endpoint to get data, another to process the data into a database and another to 
write the data to file. This can be a single endpoint with an instruction in the request, a simple
please run this SQL against the database and write the output to this database. Though an interesting
idea would be to run this query, and to create a cache of the results with a TTL of N hours, with the response 
simply providing the identity of the cache, which was created on demand and accessible by the
requestor.

Similar on the response side, the response could be, here is the data, it could be the data is over here, or 
take this request to this place (re-direction). So an instruction in the response could 
be the data has been written to this location, to get access you need to go
to this service, to get the data, or be provided a decryption key, or ephemeral access credentials..

Hopefully it is possible to see that the instruction idiom is important, as it allows translation 
and adaptation of existing services to meet the current needs, and allows solution built today
to work with yet to be discovered systems in the future. In itself the flexibility and extensibility
of an instruction is very powerful, subsequent idioms will walk through specific examples
to hopefully clarify things better.