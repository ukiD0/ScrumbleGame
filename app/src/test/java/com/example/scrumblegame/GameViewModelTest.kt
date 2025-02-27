package com.example.scrumblegame

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
        assertEquals(actual, excepted)

        actual = viewModel.handleUserInput(text = "1")
        excepted = GameUiState.Insufficient(shuffledWord = "f1")
        assertEquals(actual, excepted)

        actual = viewModel.handleUserInput(text = "f1")
        excepted = GameUiState.Sufficient(shuffledWord = "f1")
        assertEquals(actual, excepted)

        actual = viewModel.check(text = "1f")
        excepted = GameUiState.Sufficient(shuffledWord = "f1")
        assertEquals(actual, excepted)

        actual = viewModel.next()
        excepted = GameUiState.Initial(shuffledWord = "f2")
        assertEquals(actual, excepted)
    }

    @Test
    fun caseNumber2() {
        var actual: GameUiState = viewModel.init()
        var excepted: GameUiState = GameUiState.Initial(shuffledWord = "f1")
        assertEquals(actual, excepted)

        actual = viewModel.skip()
        excepted = GameUiState.Initial(shuffledWord = "f2")
        assertEquals(actual, excepted)

        actual = viewModel.handleUserInput(text = "1")
        excepted = GameUiState.Insufficient(shuffledWord = "f2")
        assertEquals(actual, excepted)

        actual = viewModel.skip()
        excepted = GameUiState.Initial(shuffledWord = "f3")
        assertEquals(actual, excepted)

        actual = viewModel.handleUserInput(text = "f")
        excepted = GameUiState.Insufficient(shuffledWord = "f3")
        assertEquals(actual, excepted)
        actual = viewModel.handleUserInput(text = "f1")
        excepted = GameUiState.Sufficient(shuffledWord = "f3")
        assertEquals(actual, excepted)

        actual = viewModel.skip()
        excepted = GameUiState.Initial(shuffledWord = "f4")
        assertEquals(actual, excepted)

        actual = viewModel.handleUserInput(text = "f")
        excepted = GameUiState.Insufficient(shuffledWord = "f4")
        assertEquals(actual, excepted)

        actual = viewModel.handleUserInput(text = "f1")
        excepted = GameUiState.Sufficient(shuffledWord = "f4")
        assertEquals(actual, excepted)

        actual = viewModel.check(text = "f1")
        excepted = GameUiState.Incorrect(shuffledWord = "f4")
        assertEquals(actual, excepted)

        actual = viewModel.skip()
        excepted = GameUiState.Initial(shuffledWord = "f5")
        assertEquals(actual, excepted)

        actual = viewModel.handleUserInput(text = "f")
        excepted = GameUiState.Insufficient(shuffledWord = "f5")
        assertEquals(actual, excepted)

        actual = viewModel.handleUserInput(text = "f1")
        excepted = GameUiState.Sufficient(shuffledWord = "f5")
        assertEquals(actual, excepted)

        actual = viewModel.check(text = "f1")
        excepted = GameUiState.Incorrect(shuffledWord = "f5")
        assertEquals(actual, excepted)

        actual = viewModel.handleUserInput(text = "f")
        excepted = GameUiState.Insufficient(shuffledWord = "f5")
        assertEquals(actual, excepted)

        actual = viewModel.handleUserInput(text = "f1")
        excepted = GameUiState.Sufficient(shuffledWord = "f5")
        assertEquals(actual, excepted)

        actual = viewModel.handleUserInput(text = "f12")
        excepted = GameUiState.Insufficient(shuffledWord = "f5")
        assertEquals(actual, excepted)
    }
}

private class FakeRepository : GameRepository {

    private val originalList = listOf(
        "1f", "2f", "3f", "4f", "5f"
    )
    private val shuffledList = originalList.map { it.reversed() }
    private var index = 0

    override fun shuffledWord(): String = shuffledList[index]

    override fun originalWord(): String = originalList[index]

    override fun next() {
        index++
        if (index == originalList.size)
            index = 0
    }

}
