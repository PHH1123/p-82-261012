package com.back

fun main() {

    App(
        WiseSayingController(
            WiseSayingService(
                WiseSayingRepository()
            )
        ),
        SystemController()
    )
        .run()
}