package com.back

class WiseSayingController {
    var wiseId = 0
    val wiseSayings = mutableListOf<WiseSaying>()

    fun write() {
        print("명언 : ")
        val wise = readln()
        print("작가 : ")
        val author = readln()
        val wiseSaying = WiseSaying(++wiseId, author, wise).also {
            wiseSayings.add(it)
        }
        println("${wiseSaying.id}번 명언이 등록되었습니다.")
    }

    fun list()  {
        println("번호 / 작가 / 명언")
        println("--------------------------")
        wiseSayings.asReversed()
            .forEach { println("${it.id} / ${it.author} / ${it.wise}") }
    }

    fun delete(rq: Rq) {
        val id = rq.getParamValueAsInt("id", 0)

        if (id <= 0) {
            println("올바른 id를 입력해주세요")
            return
        }

        val wiseSaying = wiseSayings.firstOrNull {
            it.id == id
        }
        if (wiseSaying == null) {
            println("${id}번 명언은 존재하지 않습니다.")
            return
        }
        wiseSayings.remove(wiseSaying)
        println("${id}번 명언이 삭제되었습니다.")
    }

    fun modify(rq: Rq) {
        val id = rq.getParamValueAsInt("id", 0)

        if (id <= 0) {
            println("올바른 id를 입력해주세요")
            return
        }

        val wiseSaying = wiseSayings.firstOrNull {
            it.id == id
        }
        if (wiseSaying == null) {
            println("${id}번 명언은 존재하지 않습니다.")
            return
        }

        println("명언(기존) : ${wiseSaying.wise}")
        print("명언 : ")
        val wise = readln()
        println("작가(기존) : ${wiseSaying.author}")
        print("작가 : ")
        val author = readln()
        wiseSaying.modify(author, wise)
    }
}