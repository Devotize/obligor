package com.example.obligor.domain.models

data class Promiser(
    val name: String,
    val credit: Double,
) {

    fun isPromiserEmpty() = this == EmptyPromiser

    companion object {
        val EmptyPromiser = Promiser(
            name = "",
            credit = 0.0
        )
    }
}