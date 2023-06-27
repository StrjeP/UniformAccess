# Idiom J: On Demand

The idea is to create new things on demand (or self-service) which allows new things to be created
within the platform, with the same governance and accessibility. So it would be possible to create
a new database for your partial results to provide a restore point. This database would be secured
this would also linked to access controls, so the result could be promoted for other users. The database
could also be linked to lifecycle, and be demoted to a lower cost tier if not used and eventually
moved offline.

Data Mesh - self-serve data platform https://martinfowler.com/articles/data-mesh-principles.html

HTTP header - cache (allow edge to cache result for other users)

Apache Arrow Flight / Dremio cache on demand - can't find a reference, pretty sure it was a
a feature that there was an idea that the caller could request a cache with TTL to manage space.


The extension of this is to bring things like local cache or ORM repositories into the uniform
access security, governance and assurance. Why? Well with the modern architecture we should consider
a fluid approach to deployment, so a cache could be in process, a sidecar, a distributed cache or 
remote cache. The actual deployment should be decoupled from the solution, it is to achieve
non-functional requirements. Also consider that with the cache managed consistently, it could
apply encryption to blocks in memory without the consuming application being aware, just encapsulated
within the scope of the platform.

