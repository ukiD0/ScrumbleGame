package com.example.scrumblegame

import com.example.scrumblegame.databinding.ActivityMainBinding

interface GameUiState {

    fun update(binding: ActivityMainBinding): Unit = throw IllegalStateException("")

    data class Initial(val shuffeledWord: String) : GameUiState {

    }

    data class Insufficient(val shuffeledWord: String) : GameUiState {

    }

    data class Correct(val shuffeledWord: String) : GameUiState {

    }

    data class Incorrect(val shuffeledWord: String) : GameUiState {

    }

    data class Sufficient(val shuffeledWord: String) : GameUiState {

    }
}
