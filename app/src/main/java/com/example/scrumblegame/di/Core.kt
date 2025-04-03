package com.example.scrumblegame.di

import android.content.Context
import com.example.scrumblegame.R
import com.example.scrumblegame.stats.StatsCache

class Core(context: Context, val clearViewModel: ClearViewModel) {
    val sharedPreferences =
        context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    val statsCache: StatsCache.All = StatsCache.Base(sharedPreferences)
}