# Idiom I: Locator

Locator why a new term, well in truth to not bring along any preconceptions.

The Locator is intended to provide a uniform way to locate the things that are required, this loose
definition allows a lot of imagination. The aim is within a "context" to be able to establish to 
nearest thing which meets the desire.

Simple example, say your application has a configuration file with two data repositories, one 
for stock and the other for orders. When the system wishes to query the stock level it would ask
for the data repository called stock. Within the system there is a registry or catalog which 
resolves which of the existing data repositories meets the requirement (of being called stock).

The full potential of this idiom is predicated on other idioms still to come:
* j_on_demand
* k_instructions (m_request_instructions & n_response_instructions
* o_view_specification

Building upon the simple configuration example, lets say we ask the locator for a data repository 
called stock, then during attempting to confirm the connection the locator fails. So the
locator's response is a redirection to a "distributed locator", also with a new request which
provides additional information of the context; within this domain, in the site, the request 
is for a data store with repository access pattern. The caller is redirected to the distributed
locator, which may return the data repository directly or again a redirection, maybe to a new instance
of the data repository running in the local cluster.


## Locator Suggestions

The locator could also respond with a suggestion, which could be the options available. For 
example, the request could be an aggregation for a data source with the data for dates A to C, the response 
could suggest three options, 
* perform the aggregation dates A to B from local and aggregate dates B to C in the remote data site,
* submit the aggregation to the remote site to read dates A to C from there,
* replicate dates B to C from remote data source to the local one, then run local aggregation for dates A to C

Each would have different costs / characteristics. The application / user would be best place to decide
which option is most appropriate. 



Thought, is a Locator just an "data" source? Which answers the request with the details
of the thing which can fulfil the request? Is the same pattern appearing at a different level of 
abstraction?