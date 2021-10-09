package com.qoiu.holybibleapp.presentation

import android.content.Context
import android.content.SharedPreferences
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiSelector
import com.qoiu.holybibleapp.R
import com.qoiu.holybibleapp.core.RecyclerViewMatcher
import com.qoiu.holybibleapp.presentation.main.MainActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
@LargeTest
abstract class BaseTest {

    @get:Rule
    val actTestRule = ActivityScenarioRule(MainActivity::class.java)
    protected open val booksPage = BooksPage()

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var appContext: Context

    @Before
    fun setup(){
        appContext = InstrumentationRegistry.getInstrumentation().targetContext
        sharedPreferences = appContext.getSharedPreferences("MockIdList", Context.MODE_PRIVATE)
        clear()
    }

    @After
    fun clear(){
        sharedPreferences.edit().putStringSet("MockCollapsedItemsIdKeys", emptySet()).apply()
    }


    protected fun String.checkVisible(){
        onView(withText(this))
            .check(matches(isDisplayed()))
    }

    protected fun String.tap(){
        onView(withText(this)).perform(click())
    }

    protected fun tap(position: Int){
        onView(RecyclerViewMatcher(R.id.recyclerView).atPosition(position)).perform(click())
    }

    protected fun killAppAndReturn(){
        val appName = appContext.getString(appContext.applicationInfo.labelRes)
        with(UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())){
            pressRecentApps()
            findObject(UiSelector().textStartsWith("clear")).click()
            findObject(UiSelector().text(appName)).click()
        }
    }

    protected fun String.checkDoesNotExist(){
        onView(withText(this)).check(doesNotExist())
    }

    protected fun checkItemText(position: Int, text: String){
        onView(RecyclerViewMatcher(R.id.recyclerView).atPosition(position)).check(matches(withText(text)))
    }

}