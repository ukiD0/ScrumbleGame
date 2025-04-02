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
        var expected: GameUiState = GameUiState.Initial(shuffledWord = "f1")
        assertEquals(expected, actual)

        actual = viewModel.handleUserInput(text = "1")
        expected = GameUiState.Insufficient
        assertEquals(expected, actual)

        actual = viewModel.handleUserInput(text = "1f")
        expected = GameUiState.Sufficient
        assertEquals(expected, actual)

        actual = viewModel.check(text = "1f")
        expected = GameUiState.Correct
        assertEquals(expected, actual)

        actual = viewModel.next()
        expected = GameUiState.Initial(shuffledWord = "f2")
        assertEquals(expected, actual)
    }

    /**
     * UGTC-02
     */
    @Test
    fun caseNumber2() {
        var actual: GameUiState = viewModel.init()
        var expected: GameUiState = GameUiState.Initial(shuffledWord = "f1")
        assertEquals(expected, actual)

        actual = viewModel.skip()
        expected = GameUiState.Initial(shuffledWord = "f2")
        assertEquals(expected, actual)

        actual = viewModel.handleUserInput(text = "1")
        expected = GameUiState.Insufficient
        assertEquals(expected, actual)
        actual = viewModel.skip()
        expected = GameUiState.Initial(shuffledWord = "f3")
        assertEquals(expected, actual)

        actual = viewModel.handleUserInput(text = "f")
        expected = GameUiState.Insufficient
        assertEquals(expected, actual)
        actual = viewModel.handleUserInput(text = "f1")
        expected = GameUiState.Sufficient
        assertEquals(expected, actual)
        actual = viewModel.skip()
        expected = GameUiState.Initial(shuffledWord = "f4")
        assertEquals(expected, actual)

        actual = viewModel.handleUserInput(text = "f")
        expected = GameUiState.Insufficient
        assertEquals(expected, actual)
        actual = viewModel.handleUserInput(text = "f1")
        expected = GameUiState.Sufficient
        assertEquals(expected, actual)
        actual = viewModel.check(text = "f1")
        expected = GameUiState.Incorrect
        assertEquals(expected, actual)
        actual = viewModel.skip()
        expected = GameUiState.Initial(shuffledWord = "f5")
        assertEquals(expected, actual)

        actual = viewModel.handleUserInput(text = "f")
        expected = GameUiState.Insufficient
        assertEquals(expected, actual)
        actual = viewModel.handleUserInput(text = "f1")
        expected = GameUiState.Sufficient
        assertEquals(expected, actual)
        actual = viewModel.check(text = "f1")
        expected = GameUiState.Incorrect
        assertEquals(expected, actual)
        actual = viewModel.handleUserInput(text = "f")
        expected = GameUiState.Insufficient
        assertEquals(expected, actual)
        actual = viewModel.handleUserInput(text = "f1")
        expected = GameUiState.Sufficient
        assertEquals(expected, actual)
        actual = viewModel.handleUserInput(text = "f12")
        expected = GameUiState.Insufficient
        assertEquals(expected, actual)
    }

    @Test
    fun testLastWordNext() {
        viewModel = GameViewModel(repository = FakeRepository(listOf("one", "two")))

        var actual: GameUiState = viewModel.init(isFirstRun = true)
        var expected: GameUiState = GameUiState.Initial(shuffledWord = "one".reversed())
        assertEquals(expected, actual)

        actual = viewModel.handleUserInput(text = "one")
        expected = GameUiState.Sufficient
        assertEquals(expected, actual)

        actual = viewModel.check(text = "one")
        expected = GameUiState.Correct
        assertEquals(expected, actual)

        actual = viewModel.next()
        expected = GameUiState.Initial(shuffledWord = "two".reversed())
        assertEquals(expected, actual)

        actual = viewModel.handleUserInput(text = "two")
        expected = GameUiState.Sufficient
        assertEquals(expected, actual)

        actual = viewModel.check(text = "two")
        expected = GameUiState.Correct
        assertEquals(expected, actual)

        actual = viewModel.next()
        expected = GameUiState.Finish
        assertEquals(expected, actual)
    }

    @Test
    fun testLastWordSkip() {
        viewModel = GameViewModel(repository = FakeRepository(listOf("one", "two")))

        var actual: GameUiState = viewModel.init(isFirstRun = true)
        var expected: GameUiState = GameUiState.Initial(shuffledWord = "one".reversed())
        assertEquals(expected, actual)

        actual = viewModel.handleUserInput(text = "one")
        expected = GameUiState.Sufficient
        assertEquals(expected, actual)

        actual = viewModel.check(text = "one")
        expected = GameUiState.Correct
        assertEquals(expected, actual)

        actual = viewModel.next()
        expected = GameUiState.Initial(shuffledWord = "two".reversed())
        assertEquals(expected, actual)

        actual = viewModel.skip()
        expected = GameUiState.Finish
        assertEquals(expected, actual)
    }
}

private class FakeRepository(
    private val originalList: List<String> = listOf(
        "1f", "2f", "3f", "4f", "5f", "6f"
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
        return index == originalList.size
    }

    private var input: String = ""

    override fun saveUserInput(value: String) {
        input = value
    }

    override fun userInput(): String {
        return input
    }
}
