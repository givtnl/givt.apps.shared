package net.givtapp.codeshare.extensions

val String.toCardStyle: String
    get() = this.chunked(4).joinToString(" ")

