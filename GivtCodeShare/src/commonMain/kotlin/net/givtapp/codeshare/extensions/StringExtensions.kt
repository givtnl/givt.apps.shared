package net.givtapp.codeshare.extensions

fun String.containsOnlyDigitsOrWhitespace(): Boolean {
    val filtered = this.filter { it.isDigit() || it.isWhitespace()}
    return this.length == filtered.length
}

val String.toCardStyle: String
    get() = this.chunked(4).joinToString(" ")