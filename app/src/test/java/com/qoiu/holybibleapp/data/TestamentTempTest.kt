package com.qoiu.holybibleapp.data

import org.junit.Assert.*
import org.junit.Test

class TestamentTempTest{

    @Test
    fun test_success(){
        val actual = TestamentTemp.Base()
        assert(actual.isEmpty())
        actual.save("Old")
        assert(actual.compare("Old"))
        actual.save("New")
        assert(actual.compare("New"))
    }
}