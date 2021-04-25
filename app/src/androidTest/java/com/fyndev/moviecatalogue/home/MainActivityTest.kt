package com.fyndev.moviecatalogue.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.fyndev.moviecatalogue.R
import com.fyndev.moviecatalogue.utils.DataMovie
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    private val dataMovie = DataMovie.getMovie()
    private val dataTvShow = DataMovie.getTvShow()

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun loadMovie() {
        onView(withId(R.id.rvMovie)).check(matches(isDisplayed()))
        onView(withId(R.id.rvMovie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dataMovie.size
            )
        )
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.rvMovie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.ivBackdrop)).check(matches(isDisplayed()))
        onView(withId(R.id.tvTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.tvTitle)).check(matches(withText(dataMovie[0].title)))
        onView(withId(R.id.tvDate)).check(matches(isDisplayed()))
        onView(withId(R.id.tvDate)).check(matches(withText(dataMovie[0].release)))
        onView(withId(R.id.tvRating)).check(matches(isDisplayed()))
        onView(withId(R.id.tvRating)).check(matches(withText(dataMovie[0].rating)))
        onView(withId(R.id.tvDescription)).check(matches(isDisplayed()))
        onView(withId(R.id.tvDescription)).check(matches(withText(dataMovie[0].description)))
    }

    @Test
    fun loadTvShow() {
        onView(withId(R.id.tvFragment)).perform(click())
        onView(withId(R.id.rvTvShow)).check(matches(isDisplayed()))
        onView(withId(R.id.rvTvShow)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dataTvShow.size
            )
        )
    }

    @Test
    fun loadDetailTvShow() {
        onView(withId(R.id.tvFragment)).perform(click())
        onView(withId(R.id.rvTvShow)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.ivBackdrop)).check(matches(isDisplayed()))
        onView(withId(R.id.tvTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.tvTitle)).check(matches(withText(dataTvShow[0].title)))
        onView(withId(R.id.tvDate)).check(matches(isDisplayed()))
        onView(withId(R.id.tvDate)).check(matches(withText(dataTvShow[0].release)))
        onView(withId(R.id.tvRating)).check(matches(isDisplayed()))
        onView(withId(R.id.tvRating)).check(matches(withText(dataTvShow[0].rating)))
        onView(withId(R.id.tvDescription)).check(matches(isDisplayed()))
        onView(withId(R.id.tvDescription)).check(matches(withText(dataTvShow[0].description)))
    }

}