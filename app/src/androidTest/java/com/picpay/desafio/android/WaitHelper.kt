package com.picpay.desafio.android

import android.view.View
import androidx.test.espresso.*
import org.hamcrest.CoreMatchers
import org.hamcrest.Matcher

object WaitHelper {

    fun waitForWithElement(element: ViewInteraction) {
        if (exists(
                element
            )
        )
            return
        else
            waitForWithElement(
                element
            )
    }

    private fun exists(interaction: ViewInteraction): Boolean {
        return try {
            interaction.perform(object : ViewAction {
                override fun getConstraints(): Matcher<View> {
                    return CoreMatchers.any(View::class.java)
                }

                override fun getDescription(): String {
                    return "check for existence"
                }

                override fun perform(
                    uiController: UiController?,
                    view: View?
                ) {
                }
            })
            true
        } catch (ex: AmbiguousViewMatcherException) {
            true
        } catch (ex: NoMatchingViewException) {
            false
        } catch (ex: NoMatchingRootException) {
            false
        }
    }
}