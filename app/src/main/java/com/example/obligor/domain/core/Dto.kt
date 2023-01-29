package com.example.obligor.domain.core

interface Dto<DOMAIN> {

    fun toDomain(): DOMAIN

}