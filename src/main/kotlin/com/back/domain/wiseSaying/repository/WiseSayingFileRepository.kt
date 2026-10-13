package com.back.domain.wiseSaying.repository

import com.back.AppConfig
import com.back.JsonUtil
import com.back.domain.wiseSaying.entity.WiseSaying
import java.nio.file.Path
import kotlin.io.resolve

class WiseSayingFileRepository : WiseSayingRepository {

    var lastId = 0

    val tableDirPath: Path
        get() {
            return AppConfig.dbDirPath.resolve("wiseSaying")
        }


    override fun save(wiseSaying: WiseSaying): WiseSaying {
        return wiseSaying
            .takeIf { it.isNew() }
            .also {
                wiseSaying.id = genNextId()
                saveOnDisk(wiseSaying)
            } ?: wiseSaying
    }

    fun saveLastId(lastId: Int) {
        mkTableDirsIfNotExists()

        tableDirPath.resolve("lastId.txt")
            .toFile()
            .writeText(lastId.toString())
    }

    fun loadLastId(): Int {
        // try-catch와 비슷 아래는 예외가 발생하면 0을 반환하겠다는 의미
        // finally는 못씀
        return runCatching {
            tableDirPath.resolve("lastId.txt")
                .toFile()
                .readText()
                .toInt()
        }.getOrElse { 0 }

        // 아래는 엘비스 연산자를 이용한 방식
//        return tableDirPath.resolve("lastId.txt")
//            .toFile()
//            .readText()
//            .toIntOrNull() ?: 0
    }

    fun genNextId(): Int {
        return (loadLastId() + 1).also {
            saveLastId(it)
        }
    }

    private fun saveOnDisk(wiseSaying: WiseSaying) {
        mkTableDirsIfNotExists()

        val wiseSayingFile = tableDirPath.resolve("${wiseSaying.id}.json")
        wiseSayingFile.toFile().writeText(wiseSaying.jsonStr)
    }

    private fun mkTableDirsIfNotExists() {
        tableDirPath.toFile().run {
            if (!exists()) {
                mkdirs()
            }
        }
    }

    override fun findListDesc(): List<WiseSaying> {
        return tableDirPath.toFile()
            .listFiles()
            ?.filter { it.name != "data.json" }
            ?.filter { it.name.endsWith(".json") }
            ?.map { it.readText() }
            ?.map(WiseSaying::fromJsonStr)
            .orEmpty()
    }

    override fun findById(id: Int): WiseSaying? {
        return tableDirPath
            .toFile()
            .listFiles()
            ?.find { it.name == "${id}.json" }
            ?.let { WiseSaying.fromJsonStr(it.readText()) }
    }

    override fun delete(wiseSaying: WiseSaying): Boolean {
        return tableDirPath
            .resolve("${wiseSaying.id}.json")
            .toFile()
            .takeIf { it.exists() }
            ?.delete() ?: false
    }

    override fun clear() {
        tableDirPath.toFile().deleteRecursively()
    }

    override fun build() {
        mkTableDirsIfNotExists()

        val mapList = findListDesc()
            .map(WiseSaying::map)

        JsonUtil.toString(mapList)
            .let {
                tableDirPath
                    .resolve("data.json")
                    .toFile()
                    .writeText(it)
            }
    }

    private fun filterByKeyword(
        keyword: String,
        selector: (WiseSaying) -> String
    ): List<WiseSaying> {

        val pure = keyword.replace("%", "")
        val wiseSayings = findListDesc()

        if (pure.isBlank()) return wiseSayings

        return when {
            keyword.startsWith("%") && keyword.endsWith("%") ->
                wiseSayings.filter { selector(it).contains(pure) }
            keyword.startsWith("%") ->
                wiseSayings.filter { selector(it).endsWith(pure) }
            keyword.endsWith("%") ->
                wiseSayings.filter { selector(it).startsWith(pure) }
            else ->
                wiseSayings.filter { selector(it) == pure }
        }
    }

    override fun findByAuthorLike(authorLike: String): List<WiseSaying> {
        return filterByKeyword(authorLike) { it.author }
    }

    override fun findByAuthorContent(wiseLike: String): List<WiseSaying> {
        return filterByKeyword(wiseLike) { it.wise }
    }
}