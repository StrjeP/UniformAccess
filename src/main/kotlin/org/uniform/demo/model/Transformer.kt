package org.uniform.demo.model

interface Transformer<S, T> {
    fun transform(input: S): T
}
