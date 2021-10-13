package com.qoiu.holybibleapp.data.verses.cache

import com.qoiu.holybibleapp.core.DbWrapper
import com.qoiu.holybibleapp.core.RealmProvider
import com.qoiu.holybibleapp.core.Save
import com.qoiu.holybibleapp.data.verses.VerseData
import com.qoiu.holybibleapp.data.verses.VerseDataToDbMapper
import io.realm.Realm

interface VersesCacheDataSource : Save<List<VerseData>> {

    suspend fun fetchVerses(bookId: Int, chapterId: Int, text: String="") : List<VerseDB>

    class Base(
        private val realmProvider: RealmProvider,
        private val mapper: VerseDataToDbMapper
    ) : VersesCacheDataSource {
        override suspend fun fetchVerses(bookId: Int, chapterId: Int, text: String) : List<VerseDB> {
            val min = 1_000_000 * bookId + 1000 * chapterId // TODO: 10.10.2021 Make it better
            val max = 1_000_000 * bookId + 1000 * (chapterId + 1) - 1
            realmProvider.provide().use { realm ->
                val verses = realm.where(VerseDB::class.java)
                    .between("id", min, max)
                    .findAll()
                return realm.copyFromRealm(verses)
            }
        }

        override fun save(data: List<VerseData>) {
            realmProvider.provide().use { realm ->
                realm.executeTransaction {
                    data.forEach { chapter ->
                        chapter.mapBy(mapper, VerseDbWrapper(realm))
                    }
                }
            }
        }

        private inner class VerseDbWrapper(realm: Realm) : DbWrapper.Base<VerseDB>(realm) {
            override fun dbClass(): Class<VerseDB> = VerseDB::class.java
        }
    }
}