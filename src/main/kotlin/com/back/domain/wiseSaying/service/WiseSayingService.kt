package com.back.domain.wiseSaying.service

import com.back.SingletonScope
import com.back.SingletonScope.wiseSayingMemRepository
import com.back.domain.wiseSaying.entity.WiseSaying
import com.back.domain.wiseSaying.repository.WiseSayingMemRepository
import com.back.domain.wiseSaying.repository.WiseSayingRepository

class WiseSayingService
    (val wiseSayingRepository: WiseSayingRepository = SingletonScope.wiseSayingFileRepository) {

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

    fun build() {
        wiseSayingMemRepository.build()
    }

    fun findByKeyword(keywordType: String, keyword: String): List<WiseSaying> {
        return when ( keywordType ) {
            "author" -> wiseSayingRepository.findByAuthorLike("%$keyword%")
            "content" -> wiseSayingRepository.findByAuthorContent("%$keyword%")
            else -> wiseSayingRepository.findByAuthorContent("%$keyword%")
        }
    }
}