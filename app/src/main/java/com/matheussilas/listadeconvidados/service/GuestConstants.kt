package com.matheussilas.listadeconvidados.service

class GuestConstants private constructor() {
    companion object {
        const val GUESTID = "guestId"

    }

    object FILTER {
        const val EMPTY = 0
        const val PRESENT = 1
        const val ABSENT = 2
    }

}