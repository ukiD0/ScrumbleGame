package com.example.scrumblegame

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.scrumblegame.page.GamePage
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
        activityScenarioRule.scenario.recreate()
        gamePage.assertInitialState()

        gamePage.addInput(text = "anima")
        gamePage.assertInsufficientState()//недостаточный
        activityScenarioRule.scenario.recreate()
        gamePage.assertInsufficientState()//недостаточный

        gamePage.addInput(text = "l")
        gamePage.assertSufficientState()//достаточный
        activityScenarioRule.scenario.recreate()
        gamePage.assertSufficientState()//достаточный

        gamePage.clickCheck()
        gamePage.assertCorrectState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertCorrectState()

        gamePage.clickNext()

        gamePage = GamePage(word = "auto".reversed())
        gamePage.assertInitialState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertInitialState()
    }

    /**
     * Test case number 2
     */
    @Test
    fun caseNumber2() {
        gamePage.assertInitialState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertInitialState()

        gamePage.clickSkip()
        gamePage = GamePage(word = "auto".reversed())
        gamePage.assertInitialState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertInitialState()

        gamePage.addInput("aut")
        gamePage.assertInsufficientState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertInsufficientState()

        gamePage.clickSkip()
        gamePage = GamePage(word = "anecdote".reversed())
        gamePage.assertInitialState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertInitialState()

        gamePage.addInput("anecdo")
        gamePage.assertInsufficientState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertInsufficientState()

        gamePage.addInput("te")
        gamePage.assertSufficientState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertSufficientState()

        gamePage.clickSkip()
        gamePage = GamePage(word = "alphabet".reversed())
        gamePage.assertInitialState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertInitialState()

        gamePage.addInput("alphabt")
        gamePage.assertInsufficientState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertInsufficientState()

        gamePage.addInput("e")
        gamePage.assertSufficientState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertSufficientState()

        gamePage.clickCheck()
        gamePage.assertIncorrectState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertIncorrectState()

        gamePage.clickSkip()
        gamePage = GamePage(word = "all".reversed())
        gamePage.assertInitialState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertInitialState()

        gamePage.addInput(text = "al")
        gamePage.assertInsufficientState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertInsufficientState()

        gamePage.addInput(text = "e")
        gamePage.assertSufficientState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertSufficientState()

        gamePage.clickCheck()
        gamePage.assertIncorrectState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertIncorrectState()

        gamePage.removeInputLastLetter()
        gamePage.assertInsufficientState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertInsufficientState()

        gamePage.addInput(text = "l")
        gamePage.assertSufficientState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertSufficientState()

        gamePage.removeInputLastLetter()
        gamePage.assertInsufficientState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertInsufficientState()

        gamePage.addInput(text = "e")
        gamePage.assertSufficientState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertSufficientState()

        gamePage.clickCheck()
        gamePage.assertIncorrectState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertIncorrectState()
    }
}