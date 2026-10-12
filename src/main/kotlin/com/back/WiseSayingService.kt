package com.back

class WiseSayingService {
    var wiseId = 0
    val wiseSayings = mutableListOf<WiseSaying>()

    fun add(wise: String, author: String): WiseSaying {
        val wiseSaying = WiseSaying(++wiseId, author, wise).also {
            wiseSayings.add(it)
        }
        return wiseSaying
    }

    fun findListDesc(): List<WiseSaying> = wiseSayings.asReversed()

    fun findById(id: Int): WiseSaying? =
        wiseSayings.firstOrNull { it.id == id }

    fun delete(wiseSaying: WiseSaying) = wiseSayings.remove(wiseSaying)

    fun modify(wiseSaying: WiseSaying, author: String, wise: String) = wiseSaying.modify(author, wise)
}