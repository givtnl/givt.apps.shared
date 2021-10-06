package net.givtapp.codeshare

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}