package net.givtapp.codeshare.extensions

val Int.digits: Array<Int>
    get() = this.toString().map { it.toString().toInt() }.toTypedArray()

val Int.countOfDigits: Int
    get() = this.digits.count()

val Int?.isNull: Boolean
    get() = this == null