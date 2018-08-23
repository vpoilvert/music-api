package com.zenika.musicapi.config

import org.junit.Test
import kotlin.test.assertEquals

class BookTest {

    @Test
    fun `test parse line`() {
        val line = "100. True History of the Kelly Gang by Peter Carey (2000)"

        val book = parseLine(line)

        assertEquals("True History of the Kelly Gang by Peter Carey (2000)", book)
    }
}