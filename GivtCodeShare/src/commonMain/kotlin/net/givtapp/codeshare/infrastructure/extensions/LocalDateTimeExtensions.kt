package net.givtapp.codeshare.infrastructure.extensions

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

val LocalDateTime.Companion.current: LocalDateTime
    get () = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())