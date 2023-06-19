# Idiom D: Uniform access of Domains

This is for both request and response, to put it simple "like" in HTTP land the Accepts and 
Content-Type. How the caller can specify the format of the request and the format of the
response, this can be accepted, redirected or rejected.

Can show how the caller can direct the response to format for the consumer.

**TODO**
Show an example where a request is mapped from one format to a another. 
Simple 

`"filter": { "fieldname": "value" }`
to
`where fieldname = 'value'`
 