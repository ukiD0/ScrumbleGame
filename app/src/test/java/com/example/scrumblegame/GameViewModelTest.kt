package com.example.scrumblegame

import com.example.scrumblegame.game.GameRepository
import com.example.scrumblegame.game.GameUiState
import com.example.scrumblegame.game.GameViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class GameViewModelTest {

    private lateinit var viewModel: GameViewModel

    @Before
    fun setup() {
        viewModel = GameViewModel(repository = FakeRepository())
    }

    @Test
    fun caseNumber1() {
        var actual: GameUiState = viewModel.init()
        var excepted: GameUiState = GameUiState.Initial(
            shuffledWord = "f1"
        )
        assertEquals(excepted, actual)

        actual = viewModel.handleUserInput(text = "1")
        excepted = GameUiState.Insufficient
        assertEquals(excepted, actual)

        actual = viewModel.handleUserInput(text = "1f")
        excepted = GameUiState.Sufficient
        assertEquals(excepted, actual)

        actual = viewModel.check(text = "1f")
        excepted = GameUiState.Correct
        assertEquals(excepted, actual)

        actual = viewModel.next()
        excepted = GameUiState.Initial(shuffledWord = "f2")
        assertEquals(excepted, actual)
    }

    @Test
    fun caseNumber2() {
        var actual: GameUiState = viewModel.init()
        var excepted: GameUiState = GameUiState.Initial(shuffledWord = "f1")
        assertEquals(excepted, actual)

        actual = viewModel.skip()
        excepted = GameUiState.Initial(shuffledWord = "f2")
        assertEquals(excepted, actual)

        actual = viewModel.handleUserInput(text = "1")
        excepted = GameUiState.Insufficient
        assertEquals(excepted, actual)

        actual = viewModel.skip()
        excepted = GameUiState.Initial(shuffledWord = "f3")
        assertEquals(excepted, actual)

        actual = viewModel.handleUserInput(text = "f")
        excepted = GameUiState.Insufficient
        assertEquals(excepted, actual)
        actual = viewModel.handleUserInput(text = "f1")
        excepted = GameUiState.Sufficient
        assertEquals(excepted, actual)

        actual = viewModel.skip()
        excepted = GameUiState.Initial(shuffledWord = "f4")
        assertEquals(excepted, actual)

        actual = viewModel.handleUserInput(text = "f")
        excepted = GameUiState.Insufficient
        assertEquals(excepted, actual)

        actual = viewModel.handleUserInput(text = "f1")
        excepted = GameUiState.Sufficient
        assertEquals(excepted, actual)

        actual = viewModel.check(text = "f1")
        excepted = GameUiState.Incorrect
        assertEquals(excepted, actual)

        actual = viewModel.skip()
        excepted = GameUiState.Initial(shuffledWord = "f5")
        assertEquals(excepted, actual)

        actual = viewModel.handleUserInput(text = "f")
        excepted = GameUiState.Insufficient
        assertEquals(excepted, actual)

        actual = viewModel.handleUserInput(text = "f1")
        excepted = GameUiState.Sufficient
        assertEquals(excepted, actual)

        actual = viewModel.check(text = "f1")
        excepted = GameUiState.Incorrect
        assertEquals(excepted, actual)

        actual = viewModel.handleUserInput(text = "f")
        excepted = GameUiState.Insufficient
        assertEquals(excepted, actual)

        actual = viewModel.handleUserInput(text = "f1")
        excepted = GameUiState.Sufficient
        assertEquals(excepted, actual)

        actual = viewModel.handleUserInput(text = "f12")
        excepted = GameUiState.Insufficient
        assertEquals(excepted, actual)
    }

    @Test
    fun testLastWord() {
        viewModel = GameViewModel(repository = FakeRepository(listOf("one", "two")))

        var actual: GameUiState = viewModel.init(isFirstRun = true)
        var excepted: GameUiState = GameUiState.Initial(shuffledWord = "one".reversed())
        assertEquals(excepted, actual)

        actual = viewModel.handleUserInput(text = "one")
        excepted = GameUiState.Sufficient
        assertEquals(excepted, actual)

        actual = viewModel.check(text = "one")
        excepted = GameUiState.Correct
        assertEquals(excepted, actual)

        actual = viewModel.next()
        excepted = GameUiState.Initial(shuffledWord = "two".reversed())
        assertEquals(excepted, actual)

        actual = viewModel.handleUserInput(text = "two")
        excepted = GameUiState.Sufficient
        assertEquals(excepted, actual)

        actual = viewModel.check(text = "two")
        excepted = GameUiState.Correct
        assertEquals(excepted, actual)

        actual = viewModel.next()
        excepted = GameUiState.Finish
        assertEquals(excepted, actual)
    }
}

private class FakeRepository(
    private val originalList: List<String> = listOf(
        "1f", "2f", "3f", "4f", "5f"
    )
) : GameRepository {

    private val shuffledList = originalList.map { it.reversed() }
    private var index = 0

    override fun shuffledWord(): String = shuffledList[index]

    override fun originalWord(): String = originalList[index]

    override fun next() {
        index++
        saveUserInput("")
    }

    override fun isLastWord(): Boolean {
        return index + 1 == originalList.size
    }

    private var input: String = ""

    override fun saveUserInput(value: String) {
        input = value
    }

    override fun userInput(): String {
        return input
    }

}
