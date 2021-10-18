package net.givtapp.codeshare.infrastructure.listeners

abstract class StringValueChangedListener : ValueChangedListener<String> {
    abstract override fun onValueChanged(oldValue: String, newValue: String)
}