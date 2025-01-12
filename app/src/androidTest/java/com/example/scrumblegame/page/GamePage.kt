package com.example.scrumblegame.page

import android.view.View
import android.widget.LinearLayout
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import org.hamcrest.Matcher

class GamePage(word: String) {

    //region fields
    private val containerIdMatcher: Matcher<View> = withParent(withId(R.id.rootLayout))
    private val containerClassTypeMatcher: Matcher<View> =
        withParent(isAssignableFrom(LinearLayout::class.java))

    private val shuffleWord = ShuffleWordUi(
        word = word,
        containerIdMatcher = containerIdMatcher,
        containerClassTypeMatcher = containerClassTypeMatcher
    )

    private val inputUi = InputUi(
        containerIdMatcher = containerIdMatcher,
        containerClassTypeMatcher = containerClassTypeMatcher
    )

    private val skipUi = ButtonUi(
        id = R.id.skipButton,
        textResId = R.string.skip,
        colorHex = "#FE5C5C",
        containerIdMatcher = containerIdMatcher,
        containerClassTypeMatcher = containerClassTypeMatcher
    )

    private val nextUi = ButtonUi(
        id = R.id.nextButton,
        textResId = R.string.next,
        colorHex = "#D362D5",
        containerIdMatcher = containerIdMatcher,
        containerClassTypeMatcher = container ClassTypeMatcher
    )

    private val checkUi = CheckButtonUi(
        containerIdMatcher = containerIdMatcher,
        containerClassTypeMatcher = containerClassTypeMatcher
    )
    //endregion

    fun assertInitialState() {
        shuffleWord.assertTextVisible()
        inputUi.assertInitialState()
        skipUi.assertVisible()
        nextUi.assertNotVisible()
        checkUi.assertVisibleDisabled()
    }

    fun addInput(text: String) {
        inputUi.addInput(text = text)
    }

    fun assertInsufficientState() {
        inputUi.assertInsufficientState()
        shuffleWord.assertTextVisible()
        skipUi.assertVisible()
        nextUi.assertNotVisible()
        checkUi.assertVisibleDisabled()
    }

    fun assertSufficientState() {
        inputUi.assertInsufficientState()
        shuffleWord.assertTextVisible()
        skipUi.assertVisible()
        nextUi.assertNotVisible()
        checkUi.assertVisibleEnabled()
    }

    fun clickCheck() {
        checkUi.click()
    }

    fun assertCorrectState() {
        inputUi.assertCorrectState()
        shuffleWord.assertTextVisible()
        skipUi.assertNotVisible()
        nextUi.assertVisible()
        checkUi.assertNotVisible()
    }

    fun clickNext() {
        nextUi.click()
    }

    fun clickSkip() {
        skipUi.click()
    }

    fun assertIncorrectState() {
        inputUi.assertIncorrectState()
        shuffleWord.assertTextVisible()
        skipUi.assertVisible()
        nextUi.assertNotVisible()
        checkUi.assertVisibleDisabled()
    }

    fun removeInputLastLetter() {
        inputUi.removeInputLastLetter()
    }

}