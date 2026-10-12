package com.back

class Rq(
    val cmd: String,

    ) {
    val actionName: String
    val paramMap: Map<String, String>

    init {
        val cmdBits = cmd.split("?")
        actionName = cmdBits[0]
        paramMap = if (cmdBits.size == 2) {
            cmdBits[1]
                .split("&")
                .mapNotNull {
                    val paramBits = it.split("=")
                    if (paramBits.size == 2) {
                        paramBits[0] to paramBits[1]
                    } else {
                        null
                    }
                }
                .toMap()
        } else {
            emptyMap()
        }
    }

    private fun getParamValue(key: String): String? {
        return paramMap[key]
    }

    fun getParamValueAsInt(key: String, defaultValue: Int): Int {
        return getParamValue(key)?.toIntOrNull() ?: defaultValue
    }
}