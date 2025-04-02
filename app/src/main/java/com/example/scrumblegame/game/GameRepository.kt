package com.example.scrumblegame.game

interface GameRepository {

    fun shuffledWord(): String

    fun originalWord(): String

    fun next()

    fun saveUserInput(value: String)

    fun userInput(): String

    fun isLastWord(): Boolean

    class Base(
        private val index: IntCache,
        private val userInput: StringCache,
        private val shuffleStrategy: ShuffleStrategy = ShuffleStrategy.Base(),
        private val originalList: List<String> = listOf(
            "animal",
            "auto",
            "anecdote",
            "alphabet",
            "all",
        )
    ) : GameRepository {

        private val shuffledList = originalList.map { shuffleStrategy.shuffle(it) }

        override fun shuffledWord(): String = shuffledList[index.read()]

        override fun originalWord(): String = originalList[index.read()]

        override fun next() {
            index.save(index.read() + 1)
            userInput.save("")
        }

        override fun saveUserInput(value: String) {
            userInput.save(value)
        }

        override fun userInput(): String {
            return userInput.read()
        }

        override fun isLastWord(): Boolean {
            val lastWord = index.read() == originalList.size
            if (lastWord)
                index.save(0)
            return lastWord
        }
    }

}

interface ShuffleStrategy {

    fun shuffle(source: String): String

    class Base : ShuffleStrategy {

        override fun shuffle(source: String): String {
            val oursWords = source.toCharArray()
            oursWords.shuffle()
            return oursWords.concatToString()
        }
    }

    class Reverse : ShuffleStrategy {

        override fun shuffle(source: String): String {
            return source.reversed()
        }
    }
}
