package com.qoiu.holybibleapp.presentation

import androidx.fragment.app.Fragment
import com.qoiu.holybibleapp.core.PreferenceProvider
import com.qoiu.holybibleapp.core.Read
import com.qoiu.holybibleapp.core.Save
import com.qoiu.holybibleapp.presentation.book.BooksFragment
import com.qoiu.holybibleapp.presentation.book.BooksNavigator
import com.qoiu.holybibleapp.presentation.chapter.ChaptersFragment
import com.qoiu.holybibleapp.presentation.chapter.ChaptersNavigator
import com.qoiu.holybibleapp.presentation.main.MainNavigator
import com.qoiu.holybibleapp.presentation.main.NavigationCommunication

interface Navigator : Save<Int>, Read<Int>, BooksNavigator, ChaptersNavigator, MainNavigator {

    class Base(preferenceProvider: PreferenceProvider) : Navigator {

        private val sharedPreferences =
           preferenceProvider.provideSharedPreference(NAVIGATOR_FILE_NAME)
        private val fragments = listOf(
            object : FragmentGetter {
                override fun get(): Fragment {
                    return BooksFragment()
                }
            },
            object : FragmentGetter {
                override fun get(): Fragment {
                    return ChaptersFragment()
                }
            }

        )

        override fun save(data: Int) {
            sharedPreferences.edit().putInt(CURRENT_SCREEN_KEY, data).apply()
        }

        override fun read(): Int {
            return sharedPreferences.getInt(CURRENT_SCREEN_KEY, 0)
        }

        override fun saveBookScreen() {
            save(BOOKS_SCREEN)
        }

        override fun nextScreen(navigationCommunication: NavigationCommunication) {
            navigationCommunication.map(read() + 1)
        }

        override fun saveChaptersScreen() {
            save(CHAPTERS_SCREEN)
        }

        override fun getFragment(id: Int): Fragment {
            return fragments[id].get()
        }

        private companion object {
            const val NAVIGATOR_FILE_NAME = "navigation"
            const val CURRENT_SCREEN_KEY = "screenId"
            const val BOOKS_SCREEN = 0
            const val CHAPTERS_SCREEN = 1
        }
    }
}

interface FragmentGetter {
    fun get(): Fragment
}