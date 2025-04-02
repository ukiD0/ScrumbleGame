package com.example.scrumblegame.game

import com.example.scrumblegame.stats.NavigateToStats
import com.example.scrumblegame.views.check.UpdateCheckButton
import com.example.scrumblegame.views.input.UpdateInput
import com.example.scrumblegame.views.shuffledword.UpdateText
import com.example.scrumblegame.views.visibilityButton.UpdateVisibility
import com.example.scrumblegame.views.visibilityButton.VisibilityUiState

interface GameUiState {

    fun update(
        shuffledWordTextView: UpdateText,
        inputView: UpdateInput,
        skip: UpdateVisibility,
        check: UpdateCheckButton,
        next: UpdateVisibility
    ) = Unit

    fun navigate(navigateToStats: NavigateToStats) = Unit

    object Empty : GameUiState

    abstract class Abstract(
        private val inputUiState: InputUiState,
        private val checkUiState: CheckUiState
    ) : GameUiState {
        override fun update(
            shuffledWordTextView: UpdateText,
            inputView: UpdateInput,
            skip: UpdateVisibility,
            check: UpdateCheckButton,
            next: UpdateVisibility
        ) {
            inputView.update(inputUiState)
            check.update(checkUiState)
        }
    }

    data class Initial(
        private val shuffledWord: String,
        private val userInput: String = ""
    ) : Abstract(
        InputUiState.Initial(userInput),
        CheckUiState.Disabled
    ) {
        override fun update(
            shuffledWordTextView: UpdateText,
            inputView: UpdateInput,
            skip: UpdateVisibility,
            check: UpdateCheckButton,
            next: UpdateVisibility
        ) {
            super.update(shuffledWordTextView, inputView, skip, check, next)
            shuffledWordTextView.update(shuffledWord)
            next.update(VisibilityUiState.Gone)
            skip.update(VisibilityUiState.Visible)
        }
    }

    object Insufficient : Abstract(
        InputUiState.Insufficient,
        CheckUiState.Disabled
    )

    object Sufficient : Abstract(
        InputUiState.Sufficient,
        CheckUiState.Enabled
    )

    object Correct : Abstract(
        InputUiState.Correct,
        CheckUiState.Invisible
    ) {
        override fun update(
            shuffledWordTextView: UpdateText,
            inputView: UpdateInput,
            skip: UpdateVisibility,
            check: UpdateCheckButton,
            next: UpdateVisibility
        ) {
            super.update(shuffledWordTextView, inputView, skip, check, next)
            next.update(VisibilityUiState.Visible)
            skip.update(VisibilityUiState.Gone)
        }
    }

    object Incorrect : Abstract(
        InputUiState.Incorrect,
        CheckUiState.Disabled
    )

    object Finish : GameUiState {
        override fun navigate(navigateToStats: NavigateToStats) =
            navigateToStats.navigateToStats()
    }
}
