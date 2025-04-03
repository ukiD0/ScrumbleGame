package com.example.scrumblegame

import android.app.Application
import android.content.Context
import com.example.scrumblegame.game.GameRepository
import com.example.scrumblegame.game.GameViewModel
import com.example.scrumblegame.game.IntCache
import com.example.scrumblegame.game.ShuffleStrategy
import com.example.scrumblegame.game.StringCache
import com.example.scrumblegame.stats.StatsCache
import com.example.scrumblegame.stats.StatsViewModel

class UnscrambleApp : Application() {

    lateinit var statsViewModel: StatsViewModel
    lateinit var viewModel: GameViewModel

    override fun onCreate() {
        super.onCreate()
        val sharedPreferences =
            getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE)
        val statsCache: StatsCache.All = StatsCache.Base(sharedPreferences)
        viewModel = GameViewModel(
            GameRepository.Base(
                statsCache,
                IntCache.Base(sharedPreferences, "indexKey", 0),
                StringCache.Base(sharedPreferences, "userInputKey", ""),
                ShuffleStrategy.Reverse()
            )
        )
    }
}