package com.back

class App {

    fun run() {
        println("== 명언 앱 ==")
        val wiseSayings = mutableListOf<WiseSaying>()
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
                    val wiseSaying = WiseSaying(++wiseId, author, wise).also {
                        wiseSayings.add(it)
                    }
                    println("${wiseSaying.id}번 명언이 등록되었습니다.")
                }
                "목록" -> {
                    println("번호 / 작가 / 명언")
                    println("--------------------------")
                    wiseSayings.asReversed()
                        .forEach { (id, author, wise) -> println("$id / $author / $wise") }
                }
            }
        }
    }
}