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

    private fun ActivityScenarioRule<*>.doWithRecreate(block: () -> Unit) {
        block.invoke()
        scenario.recreate()
        block.invoke()
    }
}
