# UniformAccess
Uniform Access starting with data and aim for more...

## Introduction

To me uniform access is one of the concepts, maybe a paradigm shift, which opens up a new
set of opportunities, it clears away some restrictive patterns.

I've switched this repo to be a collection of idioms and patterns, which I will add to and
improve over time. With the intention to add code to demonstrate some ways to implement them
to form larger solutions.


Why the term Uniform Access, this is not a new idea, it applies to many things. Though for techies, a good description was by Bertrand Meyer’s Uniform Access Principle, “All services offered by a module should be available through a uniform notation, which does not betray whether they are implemented through storage or through computation”. Another example from the database world is the simple activity of querying data from a Table and a View, the consumer isn’t forced to behave differently to address the view as opposed the table, one is computed the other is storage.

(using letters here to allow )
### Idiom A: Uniform Computation and Storage
So the first idiom is pretty simple, demonstrated by any RDBMS which has tables or views, or say within Spark.

`package org.uniform.idioms.a_store_compute`

### Idiom B: Uniform Batch and Stream
To me this can be considered similar to the wave-particle duality principle, where every particle or quantum entity can be perceived as either a wave or a particle. The way data is processed defines whether it is batch or stream.
Note: see data boundaries for stream

`package org.uniform.idioms.a_store_compute`

### Idiom C: Data Boundaries
For stored data, this could be for each block, to describe a range to which the data is applicable, for example in an RDBMS the partitioned table, this could be the range on the partitioning dimension (e.g. date range).
For streaming data, this could be a data dimension, when a value in the stream changes, or when the sending, or consuming.  For streaming people this would be a long chat about windowing.
For computed a similar concept,

### Idiom D: Uniform access of Domains
### Idiom D: Request format and response format
If we call transformation of one domain shape to another is a computation, then this is covered by the Uniform Computation and Storage. This is brought out as a separate idiom as the consumers projection of data can differ from the storage format. This is often seen as a consumer problem, but is best seen as a pipeline step.

### Idiom E: Tiering
The building upon the idea that the consumer is decoupled from the storage it is possible to combine many sources. The boundary of one store can instruct the next store where to continue.

### Idiom F: Parts and Whole 

For a single source, the output can be sliced int Micro Batch (bounded batch of data) or 
Batch (bounded data).

Similar a source could be partitioned, be that native or by partitioning instructions.


### Idiom: Claims and assertions

### Idiom: Identity

### Idiom: Request Instructions (to guide or instruct)
Very simply, instructions are similar to "advice" and "commands". These could be instructions
like this should only execute on this specific machine, or the results should be cached for 10 
minutes. Or the instruction could be guidance consider replicated data the request could be
for the data to be no more than N days stale.

### Idiom: Indirection through results or instruction
### Idiom: Response Instruction
Simple sketch, the response says, send "this" query to this database, then on the boundary x, take the offsets from the boundary and subscribe to the stream from this point.

### Idiom: Locator
Similar to discovery but without the baggage, the idea is a locator can be from config, 
context or service. Consider a map printed at a point in time (config), though the 
destination has moved, the caller could be redirected or contact the publisher for an 
updated map. This allows offline routing and incremental updates, without a single 
point of failure. In the locator path it is possible to respond or redirect.
The locator could orchestrate activities to assist in fulfilling the request.

### Idiom: View Specifications
This is an approach for versioned data, instead of a simple linear versioning, this could be
more. This could be to use the "best" data for the period, for market data this could be from the 
exchange or an aggregator. This could be the base data with my corrections in preference over my
teams corrections. This could be an instruction to follow the data provenance to assert the 
specification has been followed.

### Idiom: Inverting the problem
The forward problem is to take data apply a transformation to generate data and place the result in a new data store. This is a fairly well understood problem, it allows focused optimisations and delivers data proactive or reactive ways, applying the same templated process every time.
The inverted problem, I liken to working in a spreadsheet, with a question in mind, figure out what is required, then step on to what is needed. Then during the calculation phase, as each "cell" is populated the dependent "cells" are calculated until the answer is produced. The processing logic which was formed in the chain can be taken and "cast" as a forward problem for regular generation. The benefit of the inverted approach allows exploration, with a dynamically composable approach allows this exploration to be achieved, though with the potential to optimise when the solution is understood.


### Pattern: Usage of claims (evidence)
There are many places where claims are used:

Claims about a data batch (aka block), this could be simple ranges (min max), could be 
number of distinct values, quality metrics about number of nulls, or distributions, 
distinct values. Consider the parquet block information, these claims could cover that this
data joins completely with this other data.

Claims about the integrity of the data, this could be a simple md5 hash, a location on a
block chain to locate the evidence, a self describing integrity record or other.

Claims about the provenance, the exact details of the data to allow this to be recreated
if required. This could be the first layer or the entire provenance tree.



