package com.example.scrumblegame.stats.di

import com.example.scrumblegame.di.AbstractProvideViewModel
import com.example.scrumblegame.di.Core
import com.example.scrumblegame.di.Module
import com.example.scrumblegame.di.ProvideViewModel
import com.example.scrumblegame.stats.StatsRepository
import com.example.scrumblegame.stats.StatsViewModel

class ProvideGameOverViewModel(
    core: Core,
    next: ProvideViewModel
) : AbstractProvideViewModel(core, next, StatsViewModel::class.java) {

    override fun module() = GameOverModule(core)
}

class GameOverModule(
    private val core: Core
) : Module<StatsViewModel> {

    override fun viewModel() =
        StatsViewModel(StatsRepository.Base(core.statsCache), core.clearViewModel)

}