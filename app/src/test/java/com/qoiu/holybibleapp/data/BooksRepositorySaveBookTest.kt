package com.qoiu.holybibleapp.data

import com.qoiu.holybibleapp.core.Abstract
import com.qoiu.holybibleapp.data.cache.BookDB
import com.qoiu.holybibleapp.data.cache.BooksCacheDataSource
import com.qoiu.holybibleapp.data.cache.BooksCacheMapper
import com.qoiu.holybibleapp.data.net.BookCloud
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import java.net.UnknownHostException

class BooksRepositorySaveBookTest {
    @Test
    fun test_save_books()= runBlocking {
        val testCloudDataSource = TestBooksCloudDataSource()
        val testCacheDataSource = TestBooksCacheDataSource()
        val repository = BooksRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            BooksCloudMapper.Base(ToBookMapper.Base()),
            BooksCacheMapper.Base(ToBookMapper.Base())
        )

        val actualCloud = repository.fetchBooks()

        val expectedCloud = BooksData.Success(listOf(
            BookData(0, "name 0","ot"),
            BookData(1, "name 1","ot"),
            BookData(2, "name 2","ot")
        ))

        Assert.assertEquals(expectedCloud, actualCloud)

        val actualCache = repository.fetchBooks()
        val expectedCache = BooksData.Success(listOf(
            BookData(0, "name 0 db","ot"),
            BookData(1, "name 1 db","ot"),
            BookData(2, "name 2 db","ot")
        ))
        Assert.assertEquals(expectedCache, actualCache)
    }



    class TestBooksCacheDataSource() : BooksCacheDataSource {
        private val list = ArrayList<BookDB>()
        override fun fetchBooks(): List<BookDB> {
            return list
        }

        override fun saveBooks(books: List<BookData>) {
            books.map {book ->
                list.add(BookDB().apply{
                    this.map(ToBookMapper.Base())
                })
            }
        }
    }


    private inner class TestBooksCloudDataSource() : BooksCloudDataSource {
        override suspend fun fetchBooks(): List<BookCloud> {
                return listOf(
                    BookCloud(0, "name 0","ot"),
                    BookCloud(1, "name 1","ot"),
                    BookCloud(2, "name 2","ot")
                )
            }
        }
    }