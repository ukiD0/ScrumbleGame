package com.example.scrumblegame.stats

interface StatsRepository {

    fun stats(): Triple<Int, Int, Int>
    fun clear()
    //todo base class
}
