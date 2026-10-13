package com.back.domain.wiseSaying.controller

import com.back.Rq
import com.back.SingletonScope
import com.back.domain.wiseSaying.entity.WiseSaying
import com.back.domain.wiseSaying.service.WiseSayingService

class WiseSayingController(
    val wiseSayingService: WiseSayingService = SingletonScope.wiseSayingService
) {

    var wiseId = 0
    val wiseSayings = mutableListOf<WiseSaying>()

    fun write() {
        print("명언 : ")
        val wise = readln()
        print("작가 : ")
        val author = readln()

        val wiseSaying = wiseSayingService.add(wise, author)
        println("${wiseSaying.id}번 명언이 등록되었습니다.")
    }

    fun list(rq: Rq) {

        val keywordType = rq.getParamValue("keywordType", "content")
        val keyword = rq.getParamValue("keyword", "")

        val wiseSayings = if (keyword.isNotBlank())
            wiseSayingService.findByKeyword(keywordType, keyword)
        else
            wiseSayingService.findListDesc()


        if (keyword.isNotBlank()) {
            println("----------------------")
            println("검색타입 : $keywordType")
            println("검색어 : $keyword")
            println("----------------------")
        }

        println("번호 / 작가 / 명언")
        println("--------------------------")
        wiseSayings.forEach {
                println("${it.id} / ${it.author} / ${it.wise}")
            }
    }

    fun delete(rq: Rq) {
        val id = rq.getParamValueAsInt("id", 0)

        if (id <= 0) {
            println("올바른 id를 입력해주세요")
            return
        }

        val wiseSaying = wiseSayingService.findById(id)
        if (wiseSaying == null) {
            println("${id}번 명언은 존재하지 않습니다.")
            return
        }

        wiseSayingService.delete(wiseSaying)
        println("${id}번 명언이 삭제되었습니다.")
    }

    fun modify(rq: Rq) {
        val id = rq.getParamValueAsInt("id", 0)

        if (id <= 0) {
            println("올바른 id를 입력해주세요")
            return
        }

        val wiseSaying = wiseSayingService.findById(id)
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
        wiseSayingService.modify(wiseSaying, author, wise)
    }

    fun build() {
        wiseSayingService.build()
        println("data.json 파일의 내용이 갱신되었습니다.")
    }
}