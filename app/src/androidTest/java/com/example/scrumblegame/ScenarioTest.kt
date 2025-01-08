package com.example.scrumblegame

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class ScenarioTest {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    private lateinit var gamePage: GamePage

    @Before
    fun setup() {
        gamePage = GamePage(word = "animal".reversed())
    }

    /**
     * Test case number 1
     */
    @Test
    fun caseNumber1() {
        gamePage.assertInitialState()

        gamePage.addInput(text = "anima")
        gamePage.assertInsufficientState()//недостаточный

        gamePage.addInput(text = "l")
        gamePage.assertSufficientState()//достаточный

        gamePage.clickCheck()
        gamePage.assertCorrectState()

        gamePage.clickNext()

        gamePage = GamePage(word = "auto".reversed())
        gamePage.assertInitialState()

    }

    /**
     * Test case number 2
     */
    fun caseNumber2() {
        gamePage.assertInitialState()

        gamePage.clickSkip()
        gamePage = GamePage(word = "auto".reversed())
        gamePage.assertInitialState()

        gamePage.addInput("aut")
        gamePage.assertInSufficientState()

        gamePage.clickSkip()
        gamePage = GamePage(word = "anecdote".reversed())
        gamePage.assertInitialState()

        gamePage.addInput("anecdo")
        gamePage.assertInsufficientState()

        gamePage.addInput("te")
        gamePage.assertSufficientState()

        gamePage.clickSkip()
        gamePage = GamePage(word = "alphabet".reversed())
        gamePage.assertInitialState()

        gamePage.addInput("alphabt")
        gamePage.assertInSufficientState()

        gamePage.addInput("e")
        gamePage.assertSufficientState()

        gamePage.clickCheck()
        gamePage.assertIncorrectState()

        gamePage.clickSkip()
        gamePage = GamePage(word = "all".reversed())
        gamePage.assertInitialState()

        gamePage.addInput(text = "al")
        gamePage.assertInSufficientState()

        gamePage.addInput(text = "e")
        gamePage.assertSufficientState()

        gamePage.clickCheck()
        gamePage.assertIncorrectState()

        gamePage.removeInputLastLetter()
        gamePage.assertInSufficientState()

        gamePage.addInput(text = "l")
        gamePage.assertSufficientState()

        gamePage.removeInputLastLetter()
        gamePage.assertInSufficientState()

        gamePage.addInput(text = "e")
        gamePage.assertSufficientState()

        gamePage.clickCheck()
        gamePage.assertIncorrectState()
    }
}