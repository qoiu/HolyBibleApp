package com.qoiu.holybibleapp.data.chapters

import com.qoiu.holybibleapp.core.Abstract
import com.qoiu.holybibleapp.core.DbWrapper
import com.qoiu.holybibleapp.data.chapters.cache.ChapterDb
import com.qoiu.holybibleapp.presentation.chapter.ChapterUi

interface ChapterId : Abstract.Object<ChapterUi,ChapterIdToUiMapper> {
    fun min(): Int
    fun max(): Int
    fun mapToDb(db: DbWrapper<ChapterDb>) : ChapterDb

    /**
     * @param bookId [1-66]
     * @param chapterId [1001 - 66999]
     */
    class Base: ChapterId{

        private val bookId : Int
        private val chapterIdReal: Int
        private val chapterIdGenerated: Int

        constructor(
            bookId: Int,
            chapterIdReal: Int = 0,
            chapterIdGenerated: Int = 0
        ){
            this.bookId = bookId
            if(chapterIdReal == 0){
                this.chapterIdGenerated = chapterIdGenerated
                this.chapterIdReal = chapterIdGenerated % MULTIPLY
            } else{
                this.chapterIdReal = chapterIdReal
                this.chapterIdGenerated = MULTIPLY * bookId + chapterIdReal
            }
        }

        override fun min(): Int = MULTIPLY*bookId


        override fun max(): Int = MULTIPLY*(bookId+1)

        override fun mapToDb(db: DbWrapper<ChapterDb>): ChapterDb = db.createObject(chapterIdGenerated)

        override fun map(mapper: ChapterIdToUiMapper): ChapterUi = mapper.map(chapterIdGenerated,chapterIdReal)
        private companion object{
            const val MULTIPLY = 1000
        }
    }
}