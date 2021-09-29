package com.qoiu.holybibleapp.presentation

import org.junit.Assert.*
import org.junit.Test

class UiDataCacheTest {

    private inner class TestCache(): IdCache{
        private val set = HashSet<Int>()
        override fun read(): Set<Int> = set

        override fun save(id: Int) {
            set.add(id)
        }

        override fun start() {
            set.clear()
        }

        override fun finish() = Unit
    }

    private val source = listOf(
        BookUi.Testament(-1, "old"),
        BookUi.Base(1, "Old first"),
        BookUi.Base(2, "Old second"),
        BookUi.Base(3, "Old third"),
        BookUi.Testament(0, "new"),
        BookUi.Base(4, "New first"),
        BookUi.Base(5, "New second"),
        BookUi.Base(6, "New third"),
    )

    private val firstHidden = listOf(
        BookUi.Testament(-1, "old",true),
        BookUi.Testament(0, "new"),
        BookUi.Base(4, "New first"),
        BookUi.Base(5, "New second"),
        BookUi.Base(6, "New third")
    )

    private val secondHidden = listOf(
        BookUi.Testament(-1, "old"),
    BookUi.Base(1, "Old first"),
    BookUi.Base(2, "Old second"),
    BookUi.Base(3, "Old third"),
    BookUi.Testament(0, "new",true)
    )

    private val allHidden = listOf(
        BookUi.Testament(-1, "old",true),
        BookUi.Testament(0, "new",true)
    )

    @Test
    fun test_collapse_first() {
        val dataCache = UiDataCache.Base(TestCache())
        dataCache.cache(source)
        val actual = dataCache.changeState(-1)
        val expected = firstHidden
        assertEquals(expected, actual)
    }

    @Test
    fun test_collapse_second() {
        val dataCache = UiDataCache.Base(TestCache())
        dataCache.cache(source)
        val actual = dataCache.changeState(0)
        val expected = secondHidden
        assertEquals(expected, actual)
    }

    @Test
    fun test_collapse_first_then_expand() {
        val dataCache = UiDataCache.Base(TestCache())
        dataCache.cache(source)
        var actual = dataCache.changeState(-1)
        var expected = firstHidden
        assertEquals(expected, actual)
        actual = dataCache.changeState(-1)
        expected = source
        assertEquals(expected, actual)

    }

    @Test
    fun test_collapse_second_then_expand() {
        val dataCache = UiDataCache.Base(TestCache())
        dataCache.cache(source)
        var actual = dataCache.changeState(0)
        var expected =secondHidden
        assertEquals(expected, actual)
        actual = dataCache.changeState(0)
        expected = source
        assertEquals(expected, actual)
    }

    @Test
    fun test_collapse_first_collapse_second_expand_first() {
        val dataCache = UiDataCache.Base(TestCache())
        dataCache.cache(source)
        var actual = dataCache.changeState(-1)
        var expected = firstHidden
        assertEquals(expected, actual)
        actual = dataCache.changeState(0)
        expected = allHidden
        assertEquals(expected, actual)
        actual = dataCache.changeState(-1)
        expected = secondHidden
        assertEquals(expected, actual)
    }

    @Test
    fun test_collapse_secon_collapse_firstd_expand_second() {
        val dataCache = UiDataCache.Base(TestCache())
        dataCache.cache(source)
        var actual = dataCache.changeState(0)
        var expected = secondHidden
        assertEquals(expected, actual)
        actual = dataCache.changeState(-1)
        expected = allHidden
        assertEquals(expected, actual)
        actual = dataCache.changeState(0)
        expected = firstHidden
        assertEquals(expected, actual)
    }

    @Test
    fun test_collapse_first_collapse_second_expand_first_expand_second() {
        val dataCache = UiDataCache.Base(TestCache())
        dataCache.cache(source)
        var actual = dataCache.changeState(-1)
        var expected = firstHidden
        assertEquals(expected, actual)
        actual = dataCache.changeState(0)
        expected = allHidden
        assertEquals(expected, actual)
        actual = dataCache.changeState(-1)
        expected = secondHidden
        assertEquals(expected, actual)
        actual = dataCache.changeState(0)
        expected = source
        assertEquals(expected, actual)
    }
}