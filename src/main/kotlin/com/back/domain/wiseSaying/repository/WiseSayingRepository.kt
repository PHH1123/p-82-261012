package com.back.domain.wiseSaying.repository

import com.back.Page
import com.back.domain.wiseSaying.entity.WiseSaying

interface WiseSayingRepository {
    fun save(wiseSaying: WiseSaying): WiseSaying
    fun findListDesc(): List<WiseSaying>
    fun findById(id: Int): WiseSaying?
    fun delete(wiseSaying: WiseSaying): Boolean
    fun clear()
    fun build()
    fun findByAuthorLike(keyword: String): List<WiseSaying>
    fun findByAuthorContent(keyword: String): List<WiseSaying>
    fun findByKeywordPaged(keywordType: String, keyword: String, itemsPerPage: Int, pageNo: Int): Page<WiseSaying>
    fun findListDescPaged(itemsPerPage: Int, pageNo: Int): Page<WiseSaying>
}