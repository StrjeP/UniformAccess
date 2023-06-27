package org.uniform.idioms.g_identity

data class SimpleIdentity(val uniqueId: String, val typeId: String, val identityQualifiers: Map<String, Any>, val parentId: String? = null)