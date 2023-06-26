package org.uniform.idioms.h_claims_and_assertions

import org.uniform.idioms.g_identity.SimpleIdentity

data class SimpleAssertion(val dataSourceIdentity: SimpleIdentity, val assertions: Map<String, Object>) {
}
