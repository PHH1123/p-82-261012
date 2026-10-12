package com.back

data class WiseSaying(
    val id: Int,
    var author: String,
    var wise: String,
) {

    fun modify(author: String, wise: String) {
        this.author = author
        this.wise = wise
    }
}