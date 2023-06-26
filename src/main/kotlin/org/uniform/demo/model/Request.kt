package org.uniform.demo.model

interface Request
{
    // Select
    // as (Project)
    // From
    // Where
/*
    select table1.field1, table1.field2, table2.field2
    (as json)
    from table1, table2 inner join with table1 // table1.field2 equals table2.field2
    where table1.field3 == some value
    */
    // Note the joins are in metadata
}

data class BoundedRequest(val start: Double, val end: Double) : Request
data class UnBoundedRequest(val start: Double) : Request
data class TransformRequest<I, O>(val request: Request, val transformer: (i : I) -> O): Request


data class SomeStringRequest(val request: String) : Request
data class JsonRequest(val start:Double, val end: Double)
