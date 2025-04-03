package com.example.scrumblegame.game.di

import com.example.scrumblegame.di.AbstractProvideViewModel
import com.example.scrumblegame.di.Core
import com.example.scrumblegame.di.Module
import com.example.scrumblegame.di.ProvideViewModel
import com.example.scrumblegame.game.GameRepository
import com.example.scrumblegame.game.GameViewModel
import com.example.scrumblegame.game.IntCache
import com.example.scrumblegame.game.ShuffleStrategy
import com.example.scrumblegame.game.StringCache

class ProvideGameViewModel(
    core: Core,
    next: ProvideViewModel
) : AbstractProvideViewModel(core, next, GameViewModel::class.java) {

    override fun module() = GameModule(core)
}

class GameModule(private val core: Core) : Module<GameViewModel> {

    override fun viewModel() = GameViewModel(
        GameRepository.Base(
            core.statsCache,
            IntCache.Base(core.sharedPreferences, "indexKey", 0),
            StringCache.Base(core.sharedPreferences, "userInputKey", ""),
            ShuffleStrategy.Reverse()
        ),
        core.clearViewModel
    )
}
