# UniformAccess
Uniform Access starting with data and aim for more...

## Introduction

To me uniform access is one of the concepts, maybe a paradigm shift, which opens up a new
set of opportunities, it clears away some restrictive patterns.

I've switched this repo to be a collection of idioms and patterns, which I will add to and
improve over time. With the intention to add code to demonstrate some ways to implement them
to form larger solutions.


Why the term Uniform Access, this is not a new idea, it applies to many things. Though for techies, a good description was by Bertrand Meyer’s Uniform Access Principle, “All services offered by a module should be available through a uniform notation, which does not betray whether they are implemented through storage or through computation”. Another example from the database world is the simple activity of querying data from a Table and a View, the consumer isn’t forced to behave differently to address the view as opposed the table, one is computed the other is storage.


Will return to change the stores to docker instances to show how can combine various technologies without
the consumer being aware.

Will return to show the write side of uniform access, this needs "character" to be
explained, where a source/sink can offer certain capabilities, ah probably needs quite a bit to
explain these concepts


(using letters here to allow )
### Idiom A: Uniform Computation and Storage
So the first idiom is pretty simple, demonstrated by any RDBMS which has tables or views, or say within Spark.

`package org.uniform.idioms.a_store_compute`

### Idiom B: Uniform Batch and Stream
To me this can be considered similar to the wave-particle duality principle, where every particle or quantum entity can be perceived as either a wave or a particle. The way data is processed defines whether it is batch or stream.
Note: see data boundaries for stream

`package org.uniform.idioms.b_batch_stream`

### Idiom C: Data Boundaries
For stored data, this could be for each block, to describe a range to which the data is applicable, for example in an RDBMS the partitioned table, this could be the range on the partitioning dimension (e.g. date range).
For streaming data, this could be a data dimension, when a value in the stream changes, or when the sending, or consuming.  For streaming people this would be a long chat about windowing.
For computed a similar concept,

`package org.uniform.idioms.c_data_boundaries`


### Idiom D: Uniform access of Domains
### Idiom D: Request format and response format
If we call transformation of one domain shape to another is a computation, then this is covered by the Uniform Computation and Storage. This is brought out as a separate idiom as the consumers projection of data can differ from the storage format. This is often seen as a consumer problem, but is best seen as a pipeline step.

`package org.uniform.idioms.d_domain`


### Idiom E: Tiering
The building upon the idea that the consumer is decoupled from the storage it is possible to combine many sources. The boundary of one store can instruct the next store where to continue.

`package org.uniform.idioms.e_tiering`


### Idiom F: Parts and Whole

For a single source, the output can be sliced into Micro Batch (bounded batch of data) or
Batch (bounded data).

Similar a source could be partitioned, be that native or by partitioning instructions.

`package org.uniform.idioms.f_parts_and_whole`


### Idiom G: Claims and assertions
A claim is a document which contains information relating to the dataset or the work to produce a dataset. Be this a simple bulk copy report or the claims about the content. The claim is defined by the producer, so it can vary between systems. It could be a fully structured document with a schema and precise meaning or an unstructured document. The specifics of a claim would be assigned an identity to allow the content to be comprehended.

### Idiom: Identity
To allow the various things to be identifiable there should be an identity, this identity could be an absolute or qualified. For a dataset the logical meaning at a point in time, or a manifestation of it

### Idiom: Store on Demand
To allow a system to be fully flexible, the ability to store on demand is important. This is akin to writing a file, to be able

### Idiom: Instructions (to guide or instruct)
This is very simple, the idea of instructions, in the real-world this is pretty obvious, in a system this is less well understood. instructions are similar to "advice" and "commands".

### Idiom: Consistent Governance
With the various things, uniquely identifiable it is possible to introduce a consistent governance model, this isn't singularity, not a single global solution, but a consistent approach to governae.

### Idiom: Request Instructions
Very simply, request instructions are to adjust how to data is sourced, processed an potentially managed. These could be instructions
like this should only execute on this specific machine, or the results should be cached for 10
minutes. Or the instruction could be guidance consider replicated data the request could be
for the data to be no more than N days stale.

### Idiom: Response Instruction
### Idiom: Indirection through results or instruction
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

### Metadata open model
The premise of the open model, is that the metadata model is not a centrally controlled model, but a distributed ownership. There are a few core tenets of identity with versioning.
The concept is that each "thing" in the model can be associated with an aspect, the aspect will advertise the items which it suggests can be associated.
The open model is simply the metadata item has an identity, so the specific definition is stored data.

For the open model to be a true success, there should be a governance body which arbitrates between similar concepts to avoid a fragmentation of the model. Though within each domain there should be delegated authority to permit independent evolution.

## Patterns

### Pattern: Usage of claims
Claims are both assertions about the content or evidence of quality and integrity of the data or records of the task execution. These concepts can be separated, though they are so similar in implementation that they are represented here together. There are many uses for claims, below are the core concepts.

Claims about a data batch (aka block), this could be simple ranges (min max), could be
number of distinct values, quality metrics about number of nulls, or distributions,
distinct values. Consider the parquet block information, these claims could cover that this
data joins completely with this other data. This leads into over statistics, distributions, aggregations any other "fingerprint" information.

Claims about the location of the data, how to obtain this from within a larger dataset, this could be a file, the details of how to query the data, a view specification to locate this data in the various versions. The data could be stored in numerous locations, potentially in long term storage and an ephemeral cache.

Claims about the provenance, the exact details of how the data was produced, this is details of the input dataset(s) the tasks performed on the data. The provenance is a node in a graph of provenance nodes allowing the full details of how the data came about. This is a superset of lineage which shows the flow but not the specific details. Provenance increases in importance as the tasks and datasets evolve in shape and technology.

Claims about the integrity of the data, this could be a simple md5 hash, a location on a
block chain to locate the evidence, a self describing integrity record or other. With the ever growing sharing and distribution of data, the ability to prove the content is exactly what it is advertised. As data becomes an increasingly valuable asset, the ever increasing cost of training models, the is the inevitable temptation to cut costs.

Claims about the computation performed, to track the work done. Consider the data in Spark history server, this allows deep insight into the effort required to complete the work. This could be the details, or a link to the dataset where it is stored, or a combination of summary and evidence. This data can be used to gain insights into the cost of the work for performance tuning or chargeback.


### Pattern: Metadata model example for claims
At each point in the claim, it is possible to be the details or indirection details of how to obtain the details.

Claim
Identity: { id, type, version, }
Location: { details or indirection to where Identity should be presented }
Content: { data or indirection -> ranges, statistics }
Quality: { rules: {Identity, type version}  -> Dataset {Identity}  }


Tasks and Execution; simple pattern.
