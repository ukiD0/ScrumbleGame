package com.example.scrumblegame

class GameViewModel(
    private val repository: GameRepository
) {

    fun init(): GameUiState {
        val shuffledWord = repository.shuffeledWord()
        return GameUiState.Initial(shuffledWord)
    }

    fun handleUserInput(text: String): GameUiState {
        val shuffledWord = repository.shuffeledWord()
        val isSufficient = text.length == shuffledWord.length
        return if (isSufficient)
            GameUiState.Sufficient(shuffledWord)
        else
            GameUiState.Insufficient(shuffledWord)
    }

    fun check(text: String): GameUiState {
        val shuffledWord = repository.shuffeledWord()
        val originalWord = repository.originalWord()
        val isCorrect = originalWord.equals(text, ignoreCase = true)
        return if (isCorrect)
            GameUiState.Correct(shuffledWord)
        else
            GameUiState.Incorrect(shuffledWord)
    }

    fun next(): GameUiState {
        repository.next()
        return init()
    }

    fun skip(): GameUiState {
        repository.next()
        return init()
    }

}
