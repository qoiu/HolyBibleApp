package com.qoiu.holybibleapp.data.chapters.cache

import com.qoiu.holybibleapp.core.DbWrapper
import com.qoiu.holybibleapp.core.RealmProvider
import com.qoiu.holybibleapp.core.Save
import com.qoiu.holybibleapp.data.chapters.ChapterData
import com.qoiu.holybibleapp.data.chapters.ChapterDataToDbMapper
import com.qoiu.holybibleapp.data.chapters.ChapterId
import io.realm.Realm

interface ChaptersCacheDataSource : Save<List<ChapterData>>{

    fun fetchChapters(bookId: Int): List<ChapterDb>

    class Base(
        private val realm: RealmProvider,
        private val mapper: ChapterDataToDbMapper
    ) : ChaptersCacheDataSource {

        override fun fetchChapters(bookId: Int): List<ChapterDb> {
            val chapterId = ChapterId.Base(bookId)
            realm.provide().use { realm ->
                val chapters = realm.where(ChapterDb::class.java).between("id",chapterId.min(),chapterId.max()).findAll()
                return realm.copyFromRealm(chapters)
            }
        }

        override fun save(data: List<ChapterData>) {
            realm.provide().use { realm->
                realm.executeTransaction {
                    data.forEach { chapter ->
                        chapter.mapBy(mapper, ChapterDBWrapper(realm))
                    }
                }
            }
        }


        private inner class ChapterDBWrapper(realm: Realm) : DbWrapper.Base<ChapterDb>(realm){
            override fun dbClass(): Class<ChapterDb> = ChapterDb::class.java
        }
    }
}