package net.givtapp.codeshare.infrastructure.extensions

val String.toCardStyle: String
    get() = this.chunked(4).joinToString(" ")

