package com.example.scrumblegame.stats

import android.view.View
import android.widget.FrameLayout
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import com.example.scrumblegame.R
import com.example.scrumblegame.game.ButtonUi
import org.hamcrest.Matcher

class StatsPage(skips: Int, fails: Int, corrects: Int) {

    private val containerIdMatcher: Matcher<View> = withParent(withId(R.id.statsLayout))
    private val containerClassTypeMatcher: Matcher<View> =
        withParent(isAssignableFrom(FrameLayout::class.java))

    private val statsUi = StatsUi(
        containerIdMatcher,
        containerClassTypeMatcher,
        skips = skips, fails = fails, corrects = corrects
    )

    private val newGameUi = ButtonUi(
        R.id.newGameButton,
        R.string.new_game,
        "#E5F1A2",
        containerIdMatcher,
        containerClassTypeMatcher
    )

    fun assertInitialState() {
        statsUi.assertVisible()
    }

    fun clickNewGame() {
        newGameUi.click()
    }
}
