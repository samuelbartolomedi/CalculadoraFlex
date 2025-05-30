package com.example.calculadoraflex

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Rule
import org.junit.matchers.JUnitMatchers.containsString

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @get:Rule()
    val activity = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun useAppContext() {
        onView(withId(R.id.etAutonomiaCombustivel1))
            .perform(typeText("10.2"))
            .perform(ViewActions.closeSoftKeyboard())

        onView(withId(R.id.etAutonomiaCombustivel2))
            .perform(typeText("12.5"))
            .perform(ViewActions.closeSoftKeyboard())

        onView(withId(R.id.etValorLitroCombustivel1))
            .perform(typeText("4.04"))
            .perform(ViewActions.closeSoftKeyboard())

        onView(withId(R.id.etValorLitroCombustivel2))
            .perform(typeText("5.85"))
            .perform(ViewActions.closeSoftKeyboard())

        onView(withId(R.id.btCalcular))
            .perform(click())
            .perform(ViewActions.closeSoftKeyboard())

        onView(withId(R.id.tvCombustivelEficiente))
            .check(matches(withText(containsString("Abasteça com Gasolina"))))
    }
}