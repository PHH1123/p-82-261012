package com.back

fun main() {

    App(
        WiseSayingController(WiseSayingService()),
        SystemController()
    )
        .run()
}