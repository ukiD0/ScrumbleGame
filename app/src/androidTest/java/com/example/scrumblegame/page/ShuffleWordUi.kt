package com.example.scrumblegame.page

import android.view.TextureView
import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.scrumblegame.R
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher

class ShuffleWordUi(
    word: String,
    containerIdMatcher: Matcher<View>,
    containerClassTypeMatcher: Matcher<View>
) {
    private val interaction: ViewInteraction = onView(
        allOf(
            withId(R.id.shuffleWordTextView)
                    withText (word),
            containerIdMatcher,
            containerClassTypeMatcher,
            isAssignableFrom(TextureView::class.java)
        )
    )

    fun assertTextVisible() {
        interaction.check(matches(isCompletelyDisplayed()))
    }

}
