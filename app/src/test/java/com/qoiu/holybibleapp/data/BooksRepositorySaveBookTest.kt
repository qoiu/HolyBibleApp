package com.qoiu.holybibleapp.data

import com.qoiu.holybibleapp.data.cache.BookDB
import com.qoiu.holybibleapp.data.cache.BooksCacheDataSource
import com.qoiu.holybibleapp.data.cache.BooksCacheMapper
import com.qoiu.holybibleapp.data.cache.DbWrapper
import com.qoiu.holybibleapp.data.net.BookCloud
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
        override fun fetchBooks() = list

        override fun saveBooks(books: List<BookData>) {
            books.map { book ->
                list.add(book.mapTo(object : BookDataToDBMapper {
                    override fun mapToDb(
                        id: Int,
                        name: String,
                        testament: String,
                        db: DbWrapper
                    ): BookDB = BookDB().apply {
                        this.id = id
                        this.name = "$name db"
                        this.testament = testament
                    }
                }, object : DbWrapper {
                    override fun createObject(id: Int) = BookDB().apply {
                        this.id = id
                    }
                }))
            }
        }
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