package com.qoiu.holybibleapp.data

import com.qoiu.holybibleapp.core.DbWrapper
import com.qoiu.holybibleapp.data.books.*
import com.qoiu.holybibleapp.data.books.cache.BookDB
import com.qoiu.holybibleapp.data.books.cache.BooksCacheDataSource
import com.qoiu.holybibleapp.data.books.cache.BooksCacheMapper
import com.qoiu.holybibleapp.data.books.cloud.BookCloud
import com.qoiu.holybibleapp.data.books.cloud.BooksCloudDataSource
import com.qoiu.holybibleapp.data.books.cloud.BooksCloudMapper
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class BooksRepositorySaveBookTest : BaseBooksRepositoryTest() {
    @Test
    fun test_save_books() = runBlocking {
        val testCloudDataSource = TestBooksCloudDataSource()
        val testCacheDataSource = TestBooksCacheDataSource()
        val repository = BooksRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            BooksCloudMapper.Base(TestToBookMapper()),
            BooksCacheMapper.Base(TestToBookMapper())
        )

        val actualCloud = repository.fetchBooks()
        val expectedCloud = BooksData.Success(
            listOf(
                BookData(0, "name 0", "ot"),
                BookData(1, "name 1", "ot"),
                BookData(2, "name 2", "ot")
            )
        )

        Assert.assertEquals(expectedCloud, actualCloud)

        val actualCache = repository.fetchBooks()
        val expectedCache = BooksData.Success(
            listOf(
                BookData(0, "name 0 db", "ot"),
                BookData(1, "name 1 db", "ot"),
                BookData(2, "name 2 db", "ot")
            )
        )
        Assert.assertEquals(expectedCache, actualCache)
    }


    class TestBooksCacheDataSource() : BooksCacheDataSource {
        private val list = ArrayList<BookDB>()

        override fun save(data: List<BookData>) {
            data.map { book ->
                list.add(book.mapBy(object : BookDataToDBMapper {
                    override fun mapToDb(
                        id: Int,
                        name: String,
                        testament: String,
                        db: DbWrapper<BookDB>
                    ): BookDB = BookDB().apply {
                        this.id = id
                        this.name = "$name db"
                        this.testament = testament
                    }
                }, object : DbWrapper<BookDB> {
                    override fun createObject(id: Int) = BookDB().apply {
                        this.id = id
                    }
                }))
            }
        }

        override fun read(): List<BookDB> = list
    }


    private inner class TestBooksCloudDataSource() : BooksCloudDataSource {
        override suspend fun fetchBooks(): List<BookCloud> {
            return listOf(
                BookCloud(0, "name 0", "ot"),
                BookCloud(1, "name 1", "ot"),
                BookCloud(2, "name 2", "ot")
            )
        }
    }
}