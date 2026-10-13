package com.back

import com.back.domain.wiseSaying.entity.WiseSaying
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import kotlin.test.Test

class WiseSayingFileRepositoryTest {

    private val wiseSayingRepository = SingletonScope.wiseSayingFileRepository

    companion object {

        @JvmStatic
        @BeforeAll
        fun setMode() {
            AppConfig.setModeToTest()
        }
    }

//    @BeforeEach
//    fun setUp() {
//        wiseSayingRepository.clear()
//    }

    @Test
    fun `save`() {
        val wiseSaying = wiseSayingRepository
            .save(WiseSaying(wise = "나의 죽음을 적들에게 알리지 말라.", author = "충무공 이순신"))

        val isExist = wiseSayingRepository
            .tableDirPath
            .toFile()
            .listFiles()
            ?.find { it.name == "${wiseSaying.id}.json" }

        assertThat(isExist).isNotNull
    }
}