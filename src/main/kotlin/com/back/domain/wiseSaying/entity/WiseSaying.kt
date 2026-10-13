package com.back.domain.wiseSaying.entity

data class WiseSaying(
    var id: Int = 0,
    var author: String,
    var wise: String,
) {

    fun modify(author: String, wise: String): WiseSaying {
        this.author = author
        this.wise = wise
        return this
    }

    fun isNew(): Boolean {
        return id == 0
    }

    val jsonStr: String
        get() {
            return """
                {
                    "id": $id,
                    "content": "$wise",
                    "author": "$author"
                }
            """.trimIndent()
        }
}