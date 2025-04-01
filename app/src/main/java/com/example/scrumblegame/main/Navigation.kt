package com.example.scrumblegame.main

import com.example.scrumblegame.game.GameScreen
import com.example.scrumblegame.game.NavigateToGame
import com.example.scrumblegame.stats.NavigateToStats
import com.example.scrumblegame.stats.StatsScreen

interface Navigation : NavigateToGame, NavigateToStats {

    fun navigate(screen: Screen)

    override fun navigateToGame() {
        navigate(GameScreen)
    }

    override fun navigateToStats() {
        navigate(StatsScreen)
    }

}