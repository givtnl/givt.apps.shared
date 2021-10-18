package net.givtapp.codeshare.infrastructure.listeners

interface ValueChangedListener<T> {
    fun onValueChanged(oldValue: T, newValue: T)
}
