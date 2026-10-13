package com.back.domain.wiseSaying.service

import com.back.SingletonScope
import com.back.domain.wiseSaying.entity.WiseSaying
import com.back.domain.wiseSaying.repository.WiseSayingRepository

class WiseSayingService
    (val wiseSayingRepository: WiseSayingRepository = SingletonScope.wiseSayingRepository) {

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