package com.back

fun main() {

    println("== 명언 앱 ==")
    val wiseList = mutableListOf<Map<String, String>>()
    var wiseId = 0

    while (true) {
        print("명령) ")
        val input = readln()

        when (input) {
            "등록" -> {
                print("명언 : ")
                val wise = readln()
                print("작가 : ")
                val author = readln()
                val map = mutableMapOf<String, String>()
                map.put("wise", wise)
                map.put("author", author)
                wiseList.add(map)
                println("${++wiseId}번 명언이 등록되었습니다.")
            }
            "종료" -> break
        }
    }
}