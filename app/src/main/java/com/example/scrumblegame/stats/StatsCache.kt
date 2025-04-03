package com.example.scrumblegame.stats

import android.content.SharedPreferences
import com.example.scrumblegame.game.IntCache

interface StatsCache {

    interface Game {
        fun incrementSkips()
        fun incrementCorrects()
        fun incrementFails()
    }

    interface Stats {
        fun clear()
        fun data(): Triple<Int, Int, Int>
    }

    interface All : Game, Stats

    class Base(
        private val skipsCache: IntCache,
        private val failsCache: IntCache,
        private val correctsCache: IntCache
    ) : All {

        constructor(sharedPreferences: SharedPreferences) : this(
            IntCache.Base(sharedPreferences, "skips", 0),
            IntCache.Base(sharedPreferences, "fails", 0),
            IntCache.Base(sharedPreferences, "corrects", 0)
        )

        override fun incrementSkips() {
            skipsCache.save(skipsCache.read() + 1)
        }

        override fun incrementCorrects() {
            correctsCache.save(correctsCache.read() + 1)
        }

        override fun incrementFails() {
            failsCache.save(failsCache.read() + 1)
        }

        override fun clear() {
            skipsCache.save(0)
            failsCache.save(0)
            correctsCache.save(0)
        }

        override fun data(): Triple<Int, Int, Int> {
            return Triple(skipsCache.read(), correctsCache.read(), failsCache.read())
        }

    }
}