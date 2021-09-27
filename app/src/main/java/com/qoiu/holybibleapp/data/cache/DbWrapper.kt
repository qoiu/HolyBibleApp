package com.qoiu.holybibleapp.data.cache

import io.realm.Realm

interface DbWrapper {

    fun createObject(id: Int): BookDB

    class Base(private val realm: Realm) : DbWrapper{
        override fun createObject(id: Int): BookDB = realm.createObject(BookDB::class.java, id)
    }
}