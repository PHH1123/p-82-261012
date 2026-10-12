package com.back

class WiseSayingRepository {
    var wiseId = 0
    val wiseSayings = mutableListOf<WiseSaying>()

    fun save(wiseSaying: WiseSaying): WiseSaying {
        return wiseSaying
            .takeIf { it.isNew() }
            ?.also {
                wiseSaying.id = ++wiseId
                wiseSayings.add(wiseSaying)
            } ?: wiseSaying
    }

    fun findListDesc(): List<WiseSaying> = wiseSayings.asReversed()

    fun findById(id: Int): WiseSaying? =
        wiseSayings.firstOrNull { it.id == id }

    fun delete(wiseSaying: WiseSaying) = wiseSayings.remove(wiseSaying)
}