package com.back.domain.wiseSaying.repository

import com.back.Page
import com.back.domain.wiseSaying.entity.WiseSaying

class WiseSayingMemRepository : WiseSayingRepository{
    var wiseId = 0
    val wiseSayings = mutableListOf<WiseSaying>()

    override fun save(wiseSaying: WiseSaying): WiseSaying {
        return wiseSaying
            .takeIf { it.isNew() }
            ?.also {
                wiseSaying.id = ++wiseId
                wiseSayings.add(wiseSaying)
            } ?: wiseSaying
    }

    override fun findListDesc(): List<WiseSaying> = wiseSayings.asReversed()

    override fun findById(id: Int): WiseSaying? =
        wiseSayings.firstOrNull { it.id == id }

    override fun delete(wiseSaying: WiseSaying): Boolean = wiseSayings.remove(wiseSaying)

    override fun clear() {
        wiseId = 0
        wiseSayings.clear()
    }

    override fun build() {
        println("WiseSayingMemRepository builded")
    }

    override fun findByAuthorLike(keyword: String): List<WiseSaying> {
        return listOf()
    }

    override fun findByAuthorContent(keyword: String): List<WiseSaying> {
        return listOf()
    }

    override fun findByKeywordPaged(
        keywordType: String,
        keyword: String,
        itemsPerPage: Int,
        pageNo: Int
    ): Page<WiseSaying> {
        TODO("Not yet implemented")
    }

    override fun findListDescPaged(itemsPerPage: Int, pageNo: Int): Page<WiseSaying> {
        TODO("Not yet implemented")
    }
}