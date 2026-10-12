package com.back

class WiseSayingService
    (val wiseSayingRepository: WiseSayingRepository) {

    fun add(wise: String, author: String): WiseSaying {
        WiseSaying(author = author, wise = wise).also {
            return wiseSayingRepository.save(it)
        }
    }

    fun findListDesc(): List<WiseSaying> = wiseSayingRepository.findListDesc()

    fun findById(id: Int): WiseSaying? =
        wiseSayingRepository.findById(id)

    fun delete(wiseSaying: WiseSaying) = wiseSayingRepository.delete(wiseSaying)

    fun modify(wiseSaying: WiseSaying, author: String, wise: String) = wiseSaying.modify(author, wise)
}