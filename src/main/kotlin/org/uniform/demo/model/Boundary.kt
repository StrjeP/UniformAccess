package org.uniform.demo.model

interface Boundary {
}

data class EndDataBoundary(val x: Double) : Boundary
data class EndDataStartFromBoundary(val x: Double, val startFrom: Double) : Boundary