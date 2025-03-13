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

    private fun ActivityScenarioRule<*>.doWithRecreate(block: () -> Unit) {
        block.invoke()
        scenario.recreate()
        block.invoke()
    }

    /**
     * Test case number 1
     */
    @Test
    fun caseNumber1() {
        activityScenarioRule.doWithRecreate(gamePage::assertInitialState)

        gamePage.addInput(text = "anima")
        activityScenarioRule.doWithRecreate(gamePage::assertInsufficientState)//недостаточный

        gamePage.addInput(text = "l")
        activityScenarioRule.doWithRecreate(gamePage::assertSufficientState)//достаточный

        gamePage.clickCheck()
        activityScenarioRule.doWithRecreate(gamePage::assertCorrectState)

        gamePage.clickNext()

        gamePage = GamePage(word = "auto".reversed())
        activityScenarioRule.doWithRecreate(gamePage::assertInitialState)
    }

    /**
     * Test case number 2
     */
    @Test
    fun caseNumber2() {
        activityScenarioRule.doWithRecreate {
            gamePage.assertInitialState()
        }

        gamePage.clickSkip()
        gamePage = GamePage(word = "auto".reversed())
        activityScenarioRule.doWithRecreate {
            gamePage.assertInitialState()
        }

        gamePage.addInput("aut")
        activityScenarioRule.doWithRecreate {
            gamePage.assertInsufficientState()
        }

        gamePage.clickSkip()
        gamePage = GamePage(word = "anecdote".reversed())
        activityScenarioRule.doWithRecreate {
            gamePage.assertInitialState()
        }

        gamePage.addInput("anecdo")
        activityScenarioRule.doWithRecreate {
            gamePage.assertInsufficientState()
        }

        gamePage.addInput("te")
        activityScenarioRule.doWithRecreate {
            gamePage.assertSufficientState()
        }

        gamePage.clickSkip()
        gamePage = GamePage(word = "alphabet".reversed())
        activityScenarioRule.doWithRecreate {
            gamePage.assertInitialState()
        }

        gamePage.addInput("alphabt")
        activityScenarioRule.doWithRecreate {
            gamePage.assertInsufficientState()
        }

        gamePage.addInput("e")
        activityScenarioRule.doWithRecreate {
            gamePage.assertSufficientState()
        }

        gamePage.clickCheck()
        activityScenarioRule.doWithRecreate {
            gamePage.assertIncorrectState()
        }

        gamePage.clickSkip()
        gamePage = GamePage(word = "all".reversed())
        activityScenarioRule.doWithRecreate {
            gamePage.assertInitialState()
        }

        gamePage.addInput(text = "al")
        activityScenarioRule.doWithRecreate {
            gamePage.assertInsufficientState()
        }

        gamePage.addInput(text = "e")
        activityScenarioRule.doWithRecreate {
            gamePage.assertSufficientState()
        }

        gamePage.clickCheck()
        activityScenarioRule.doWithRecreate {
            gamePage.assertIncorrectState()
        }

        gamePage.removeInputLastLetter()
        activityScenarioRule.doWithRecreate {
            gamePage.assertInsufficientState()
        }

        gamePage.addInput(text = "l")
        activityScenarioRule.doWithRecreate {
            gamePage.assertSufficientState()
        }

        gamePage.removeInputLastLetter()
        activityScenarioRule.doWithRecreate {
            gamePage.assertInsufficientState()
        }

        gamePage.addInput(text = "e")
        activityScenarioRule.doWithRecreate {
            gamePage.assertSufficientState()
        }

        gamePage.clickCheck()
        activityScenarioRule.doWithRecreate {
            gamePage.assertIncorrectState()
        }
    }
}