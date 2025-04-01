package com.example.scrumblegame

import android.app.Application
import android.content.Context
import com.example.scrumblegame.game.GameRepository
import com.example.scrumblegame.game.GameViewModel
import com.example.scrumblegame.game.IntCache
import com.example.scrumblegame.game.ShuffleStrategy
import com.example.scrumblegame.game.StringCache

class UnscrambleApp : Application() {

    lateinit var statsViewModel: StatsViewModel
    lateinit var viewModel: GameViewModel

    override fun onCreate() {
        super.onCreate()
        val sharedPreferences =
            getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE)
        viewModel = GameViewModel(
            GameRepository.Base(
                IntCache.Base(sharedPreferences, "indexKey", 0),
                StringCache.Base(sharedPreferences, "userInputKey", ""),
                ShuffleStrategy.Reverse()
            )
        )
    }
}