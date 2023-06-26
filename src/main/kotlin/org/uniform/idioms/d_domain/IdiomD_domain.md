# Idiom D: Uniform access of Domains

This is for both request and response, to put it simply it is "like" in HTTP vocabulary the Accepts and 
Content-Type. How the caller can specify the format of the request and the format of the
response, this can be accepted, redirected or rejected.

Can show how the caller can direct the response to format for the consumer.

The Request Demo takes a string request and creates a bounded request.

The Response Demo, provides a transformer to convert the XnY to a String.


**TODO**
Show an example where a request is mapped from one format to a another. 
Simple 

`"filter": { "fieldname": "value" }`
to
`where fieldname = 'value'`
 