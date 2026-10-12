package com.back

class App {

    fun run() {
        println("== 명언 앱 ==")
        val wiseList = mutableListOf<Map<String, String>>()
        var wiseId = 0

        while (true) {
            print("명령) ")
            val input = readln()

            when (input) {
                "종료" -> break
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

                "목록" -> {
                    println("번호 / 작가 / 명언")
                    println("--------------------------")
                    (wiseId - 1 downTo 0).forEach { i ->
                        val map = wiseList.get(i)
                        println("${i + 1} / ${map.getValue("wise")} / ${map.getValue("author")}")
                    }
                }
            }
        }
    }
}