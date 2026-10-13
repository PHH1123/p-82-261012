package com.back.domain.wiseSaying.entity

import com.back.JsonUtil

data class WiseSaying(
    var id: Int = 0,
    var author: String,
    var wise: String,
) {

    constructor(author: String, wise: String) : this(0, author, wise)

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
                    "wise": "$wise",
                    "author": "$author"
                }
            """.trimIndent()
        }

    companion object {
        fun fromJsonStr(jsonStr: String): WiseSaying {
            val map = JsonUtil.jsonStrToMap(jsonStr)

            return WiseSaying(
                id = map["id"] as Int,
                wise = map["wise"] as String,
                author = map["author"] as String,
            )
        }
    }
}