package com.example.scrumblegame

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test


class GameViewModelTest {

    private lateinit var viewModel: GameViewModel

    @Before
    fun setup() {
        viewModel = GameViewModel(repository = FakeRepository)
    }

    @Test
    fun caseNumber1() {
        var actual: GameUiState = viewModel.init()
        var excepted: GameUiState = GameUiState.Initial(
            shuffeledWord = "f1"
        )
        assertEquals(actual, excepted)

        actual = viewModel.handleUserInput(text = "f")
        excepted = GameUiState.Insufficient(shuffeledWord = "f1")
        assertEquals(actual, excepted)

        actual = viewModel.handleUserInput(text = "f1")
        excepted = GameUiState.Sufficent(shuffeledWord = "f1")
        assertEquals(actual, excepted)

        actual = viewModel.check(text = "f1")
        excepted = GameUiState.Correct(shuffeledWord = "f1")
        assertEquals(actual, excepted)

        actual = viewModel.next()
        excepted = GameUiState.Initial(shuffeledWord = "f2")
        assertEquals(actual, excepted)
    }

    @Test
    fun caseNumber2() {
        var actual: GameUiState = viewModel.init()
        var excepted: GameUiState = Initial(shuffeledWord = "f1")
        assertEquals(actual, excepted)

        actual = GameUiState.skip()
        excepted = GameUiState.Initial(shuffeledWord = "f2")
        assertEquals(actual, excepted)

        actual = viewModel.handleUserInput(text = "f")
        excepted = GameUiState.Insufficient(shuffeledWord = "f2")
        assertEquals(actual, excepted)

        actual = viewModel.skip()
        excepted = GameUiState.Initial(shuffeledWord = "f3")
        assertEquals(actual, excepted)

        actual = viewModel.handleUserInput(text = "f")
        excepted = GameUiState.Insufficient(shuffeledWord = "f3")
        assertEquals(actual, excepted)
        actual = viewModel.handleUserInput(text = "f1")
        excepted = GameUiState.Insufficient(shuffeledWord = "f3")
        assertEquals(actual, excepted)

        actual = viewModel.skip()
        excepted = GameUiState.Initial(shuffeledWord = "f4")
        assertEquals(actual, excepted)

        actual = viewModel.handleUserInput(text = "f")
        excepted = GameUiState.Insufficient(shuffeledWord = "f4")
        assertEquals(actual, excepted)

        actual = viewModel.handleUserInput(text = "f1")
        excepted = GameUiState.Sufficent(shuffeledWord = "f4")
        assertEquals(actual, excepted)

        actual = viewModel.check(text = "f1")
        excepted = GameUiState.Incorrect(shuffeledWord = "f4")
        assertEquals(actual, excepted)

        actual = viewModel.skip()
        excepted = GameUiState.Initial(shuffeledWord = "f5")
        assertEquals(actual, excepted)

        actual = viewModel.handleUserInput(text = "f")
        excepted = GameUiState.Insufficient(shuffeledWord = "f5")
        assertEquals(actual, excepted)

        actual = viewModel.handleUserInput(text = "f1")
        excepted = GameUiState.Sufficent(shuffeledWord = "f5")
        assertEquals(actual, excepted)

        actual = viewModel.check(text = "f1")
        excepted = GameUiState.Incorrect(shuffeledWord = "f5")
        assertEquals(actual, excepted)

        actual = viewModel.handleUserInput(text = "f")
        excepted = GameUiState.Insufficient(shuffeledWord = "f5")
        assertEquals(actual, excepted)

        actual = viewModel.handleUserInput(text = "f1")
        excepted = GameUiState.Sufficient(shuffeledWord = "f5")
        assertEquals(actual, excepted)

        actual = viewModel.handleUserInput(text = "f12")
        excepted = GameUiState.Insufficient(shuffeledWord = "f5")
        assertEquals(actual, excepted)
    }
}

private class FakeRepository : GameRepository {
    //todo
}
