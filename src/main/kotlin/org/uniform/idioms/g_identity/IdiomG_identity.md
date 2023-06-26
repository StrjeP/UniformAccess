# Idiom G: Identity

Identity is to allow two similar things to be distinguished from one another. Why? 
Well the identifying the differences whilst also conveying the similarities. 
This is to allow a precise reproducibility of the outcome, but also, allow the substitution
of things which achieve the same outcome.

Consider the real world, if you wanted to watch a movie, then reaching for a Blu-ray versus
opening a streaming application. Two different technologies which achieve the same outcome, of 
watching a movie. Each come with different characteristics and constraints. The Blu-ray requires
additional hardware to play the disc, whilst it comes with the benefit of not
requiring a stable internet connection. The streaming application has the benefit
of being able to play at a resolution which matches the device and isn't prone to the risk
or permanent damage from a scratch.

In code, this could be a combination of the version control location and the specific
commit which was used to build the package. This in itself would be sufficient to recreate a 
copy of the software.

For database this could be the specific network name and port, the actual hardware being used.
With details of the specific type and version of the database being used. This shows the 
potential of many connected parts. The physical and logical, so you can identify what else
is running on the hardware hogging all the RAM, or the details of all the instances of a specific version of 
database software to patch.

Identity can be applied to data, the logical and physical, it could be the record of rainfall
data, where this specific update contains the data for these dates and these locations. So it 
is possible to associate the specific data update with the coverage across the data's dimensions.
The data could be raw sensor data, or normalized, or averaged over a period. Each of these
concepts should be attached to the qualifiers of an identity to allow ways to determine the
best data to answer questions.

To that end, a simple identity class has:
uniqueId
typeId
parentId - provenance
qualifiers

`data class SimpleIdentity(val uniqueId: String, val typeId: String, val parentId: String?, val identityQualifiers: Map<String, Object>)`