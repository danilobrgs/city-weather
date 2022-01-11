package com.picpay.desafio.android

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.picpay.desafio.android.presentation.cities.CitiesActivity
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test


class CitiesActivityTest {

    @get:Rule
    private val activityTestRule = ActivityTestRule(CitiesActivity::class.java)

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    private fun launch() {
        activityTestRule.launchActivity(null)
    }

    @Test
    fun shouldDisplayTitle() {
        launch()
        val expectedTitle = context.getString(R.string.cities_activity_title)

        onView(withText(expectedTitle)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldDisplayListItem() {

        launch()

        WaitHelper.waitForWithElement(onView(withText("Lisbon")))
        onView(Matchers.allOf(withText("Lisbon")))
            .check(matches(isDisplayed()))
    }
}