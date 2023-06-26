package org.uniform.idioms.g_identity

data class SimpleIdentity(val uniqueId: String, val typeId: String, val parentId: String?, val identityQualifiers: Map<String, Object>)