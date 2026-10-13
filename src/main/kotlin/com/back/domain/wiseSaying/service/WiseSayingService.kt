package com.back.domain.wiseSaying.service

import com.back.SingletonScope
import com.back.domain.wiseSaying.entity.WiseSaying
import com.back.domain.wiseSaying.repository.WiseSayingMemRepository

class WiseSayingService
    (val wiseSayingMemRepository: WiseSayingMemRepository = SingletonScope.wiseSayingMemRepository) {

    fun add(wise: String, author: String): WiseSaying {
        WiseSaying(author = author, wise = wise).also {
            return wiseSayingMemRepository.save(it)
        }
    }

    fun findListDesc(): List<WiseSaying> = wiseSayingMemRepository.findListDesc()

    fun findById(id: Int): WiseSaying? =
        wiseSayingMemRepository.findById(id)

    fun delete(wiseSaying: WiseSaying) = wiseSayingMemRepository.delete(wiseSaying)

    fun modify(wiseSaying: WiseSaying, author: String, wise: String) = wiseSaying.modify(author, wise)
}