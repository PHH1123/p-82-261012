package com.back

import com.back.domain.system.controller.SystemController
import com.back.domain.wiseSaying.controller.WiseSayingController
import com.back.domain.wiseSaying.repository.WiseSayingRepository
import com.back.domain.wiseSaying.service.WiseSayingService

object SingletonScope {

    val wiseSayingRepository by lazy { WiseSayingRepository() }
    val wiseSayingService by lazy { WiseSayingService(wiseSayingRepository) }
    val wiseSayingController by lazy { WiseSayingController(wiseSayingService) }
    val systemController by lazy { SystemController() }
}