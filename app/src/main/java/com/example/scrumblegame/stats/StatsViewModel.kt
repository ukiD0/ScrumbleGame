package com.example.scrumblegame.stats

import com.example.scrumblegame.di.ClearViewModel
import com.example.scrumblegame.di.MyViewModel
import com.example.scrumblegame.views.stats.StatsUiState

class StatsViewModel(
    private val repository: StatsRepository,
    private val clearViewModel: ClearViewModel
) : MyViewModel {

    fun init(isFirstRun: Boolean): StatsUiState {
        return if (isFirstRun) {
            val (skips, corrects, fails) = repository.stats()
            repository.clear()
            StatsUiState.Base(skips, corrects, fails)
        } else {
            StatsUiState.Empty
        }
    }

    fun clear() {
        clearViewModel.clear(StatsViewModel::class.java)
    }
}