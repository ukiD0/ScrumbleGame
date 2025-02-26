package com.example.scrumblegame

interface GameRepository {

    fun shuffeledWord(): String

    fun originalWord(): String

    fun next()

}
