package com.example.scrumblegame

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.scrumblegame.game.GamePage
import com.example.scrumblegame.stats.StatsPage
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class ScenarioTest {

    @get:Rule
    var scenarioRule = ActivityScenarioRule(MainActivity::class.java)

    private lateinit var gamePage: GamePage

    @Before
    fun setup() {
        gamePage = GamePage(word = "animal".reversed())
    }

    /**
     * UGTC-01
     */
    @Test
    fun caseNumber1() {
        scenarioRule.doWithRecreate(gamePage::assertInitialState)

        gamePage.addInput(text = "anima")
        scenarioRule.doWithRecreate(gamePage::assertInsufficientState)

        gamePage.addInput(text = "l")
        scenarioRule.doWithRecreate(gamePage::assertSufficientState)

        gamePage.clickCheck()
        scenarioRule.doWithRecreate(gamePage::assertCorrectState)

        gamePage.clickNext()

        gamePage = GamePage(word = "auto".reversed())
        scenarioRule.doWithRecreate(gamePage::assertInitialState)
    }

    /**
     * UGTC-02
     */
    @Test
    fun caseNumber2() {
        scenarioRule.doWithRecreate {
            gamePage.assertInitialState()
        }

        gamePage.clickSkip()
        gamePage = GamePage(word = "auto".reversed())
        scenarioRule.doWithRecreate {
            gamePage.assertInitialState()
        }
        gamePage.addInput(text = "aut")
        scenarioRule.doWithRecreate {
            gamePage.assertInsufficientState()
        }
        gamePage.clickSkip()
        gamePage = GamePage(word = "anecdote".reversed())
        scenarioRule.doWithRecreate {
            gamePage.assertInitialState()
        }

        gamePage.addInput(text = "anecdot")
        scenarioRule.doWithRecreate {
            gamePage.assertInsufficientState()
        }

        gamePage.addInput(text = "e")
        scenarioRule.doWithRecreate {
            gamePage.assertSufficientState()
        }

        gamePage.clickSkip()
        gamePage = GamePage(word = "alphabet".reversed())
        scenarioRule.doWithRecreate {
            gamePage.assertInitialState()
        }

        gamePage.addInput(text = "alphabt")
        scenarioRule.doWithRecreate {
            gamePage.assertInsufficientState()
        }

        gamePage.addInput(text = "e")
        scenarioRule.doWithRecreate {
            gamePage.assertSufficientState()
        }

        gamePage.clickCheck()
        scenarioRule.doWithRecreate {
            gamePage.assertIncorrectState()
        }

        gamePage.clickSkip()
        gamePage = GamePage(word = "all".reversed())
        scenarioRule.doWithRecreate {
            gamePage.assertInitialState()
        }

        gamePage.addInput(text = "al")
        scenarioRule.doWithRecreate {
            gamePage.assertInsufficientState()
        }

        gamePage.addInput(text = "e")
        scenarioRule.doWithRecreate {
            gamePage.assertSufficientState()
        }

        gamePage.clickCheck()
        scenarioRule.doWithRecreate {
            gamePage.assertIncorrectState()
        }

        gamePage.removeInputLastLetter()
        scenarioRule.doWithRecreate {
            gamePage.assertInsufficientState()
        }

        gamePage.addInput(text = "l")
        scenarioRule.doWithRecreate {
            gamePage.assertSufficientState()
        }

        gamePage.removeInputLastLetter()
        scenarioRule.doWithRecreate {
            gamePage.assertInsufficientState()
        }

        gamePage.addInput(text = "e")
        scenarioRule.doWithRecreate {
            gamePage.assertSufficientState()
        }

        gamePage.clickCheck()
        scenarioRule.doWithRecreate {
            gamePage.assertIncorrectState()
        }
    }

    /**
     * UGTC-03
     */
    @Test
    fun caseNumber3() {
        scenarioRule.doWithRecreate(gamePage::assertInitialState)
        gamePage.clickSkip()

        gamePage = GamePage(word = "auto".reversed())
        scenarioRule.doWithRecreate(gamePage::assertInitialState)

        gamePage.addInput("autx")
        scenarioRule.doWithRecreate(gamePage::assertSufficientState)

        gamePage.clickCheck()
        scenarioRule.doWithRecreate(gamePage::assertIncorrectState)

        gamePage.removeInputLastLetter()
        scenarioRule.doWithRecreate(gamePage::assertInsufficientState)

        gamePage.addInput("0")
        scenarioRule.doWithRecreate(gamePage::assertSufficientState)

        gamePage.clickCheck()
        scenarioRule.doWithRecreate(gamePage::assertCorrectState)

        gamePage.clickNext()
        gamePage = GamePage(word = "anecdote".reversed())
        scenarioRule.doWithRecreate(gamePage::assertInitialState)

        gamePage.addInput("anecdote")
        scenarioRule.doWithRecreate(gamePage::assertSufficientState)

        gamePage.clickCheck()
        scenarioRule.doWithRecreate(gamePage::assertCorrectState)

        gamePage.clickNext()
        gamePage = GamePage(word = "alphabet".reversed())
        scenarioRule.doWithRecreate(gamePage::assertInitialState)

        gamePage.addInput("alphabed")
        scenarioRule.doWithRecreate(gamePage::assertSufficientState)

        gamePage.clickCheck()
        scenarioRule.doWithRecreate(gamePage::assertIncorrectState)

        gamePage.removeInputLastLetter()
        scenarioRule.doWithRecreate(gamePage::assertInsufficientState)

        gamePage.addInput("r")
        scenarioRule.doWithRecreate(gamePage::assertSufficientState)

        gamePage.clickCheck()
        scenarioRule.doWithRecreate(gamePage::assertIncorrectState)

        gamePage.clickSkip()
        gamePage = GamePage(word = "all".reversed())
        scenarioRule.doWithRecreate(gamePage::assertInitialState)

        gamePage.clickSkip()
        val statsPage = StatsPage(
            skips = 3, fails = 3, corrects = 2
        )
        scenarioRule.doWithRecreate(statsPage::assertInitialState)

        statsPage.clickNewGame()
        setup()
        scenarioRule.doWithRecreate(gamePage::assertInitialState)

    }

    private fun ActivityScenarioRule<*>.doWithRecreate(block: () -> Unit) {
        block.invoke()
        scenario.recreate()
        block.invoke()
    }
}
