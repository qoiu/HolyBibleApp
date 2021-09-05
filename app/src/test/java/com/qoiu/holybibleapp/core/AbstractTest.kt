package com.qoiu.holybibleapp.core

import org.junit.Test
import java.io.IOException
import java.lang.Exception

class AbstractTest {

    @Test
    fun test_success() {
        val dataObject = TestDataObject.Success("a","b")
        val domainObject = dataObject.map(DataMapper.Base())
        assert(domainObject is DomainObject.Success)
    }

    @Test
    fun test_fail() {
        val dataObject = TestDataObject.Fail(IOException())
        val domainObject = dataObject.map(DataMapper.Base())
        assert(domainObject is DomainObject.Fail)
    }

    private sealed class TestDataObject : Abstract.Object<DomainObject, DataMapper>() {
        abstract override fun map(mapper: DataMapper): DomainObject

        class Success(private val textOne: String, private val textTwo: String) : TestDataObject() {
            override fun map(mapper: DataMapper): DomainObject {
                return mapper.map(textOne,textTwo)
            }
        }

        class Fail(private val exception: Exception) : TestDataObject() {
            override fun map(mapper: DataMapper): DomainObject {
                return mapper.map(exception)
            }
        }
    }

    private interface DataMapper : Abstract.Mapper {

        fun map(textOne: String,textTwo: String): DomainObject

        fun map(exception: Exception): DomainObject

        class Base: DataMapper{
            override fun map(textOne: String, textTwo: String): DomainObject {
                return DomainObject.Success("$textOne $textTwo")
            }

            override fun map(exception: Exception): DomainObject {
                return DomainObject.Fail()
            }
        }
    }

    private sealed class DomainObject: Abstract.Object<UIObject, DomainToUIMapper>() {
        class Success(private val textCombine: String): DomainObject(){
            override fun map(mapper: DomainToUIMapper): UIObject {
                TODO("Not yet implemented")
            }

        }

        class Fail: DomainObject(){
            override fun map(mapper: DomainToUIMapper): UIObject {
                TODO("Not yet implemented")
            }
        }
    }

    interface DomainToUIMapper: Abstract.Mapper

    sealed class UIObject
}