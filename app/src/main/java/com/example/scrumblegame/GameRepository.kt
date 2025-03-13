package com.example.scrumblegame

interface GameRepository {

    fun shuffledWord(): String

    fun originalWord(): String

    fun next()

    fun saveUserInput(value: String)

    fun userInput(): String

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
            "awesome",
            "arise",
            "balloon",
            "basket",
            "bench",
            "best",
            "birthday",
            "book",
            "briefcase",
            "camera",
            "camping",
            "candle",
            "cat",
            "cauliflower",
            "chat",
            "children",
            "class",
            "classic",
            "classroom",
            "coffee",
            "colorful",
            "cookie",
            "creative",
            "cruise",
            "dance",
            "daytime",
            "dinosaur",
            "doorknob",
            "dine",
            "dream",
            "dusk",
            "eating",
            "elephant",
            "emerald",
            "eerie",
            "electric",
            "finish",
            "flowers",
            "follow",
            "fox",
            "frame",
            "free",
            "frequent",
            "funnel",
            "green",
            "guitar",
            "grocery",
            "glass",
            "great",
            "giggle",
            "haircut",
            "half",
            "homemade",
            "happen",
            "honey",
            "hurry",
            "hundred",
            "ice",
            "igloo",
            "invest",
            "invite",
            "icon",
            "introduce",
            "joke",
            "jovial",
            "journal",
            "jump",
            "join",
            "kangaroo",
            "keyboard",
            "kitchen",
            "koala",
            "kind",
            "kaleidoscope",
            "landscape",
            "late",
            "laugh",
            "learning",
            "lemon",
            "letter",
            "lily",
            "magazine",
            "marine",
            "marshmallow",
            "maze",
            "meditate",
            "melody",
            "minute",
            "monument",
            "moon",
            "motorcycle",
            "mountain",
            "music",
            "north",
            "nose",
            "night",
            "name",
            "never",
            "negotiate",
            "number",
            "opposite",
            "octopus",
            "oak",
            "order",
            "open",
            "polar",
            "pack",
            "painting",
            "person",
            "picnic",
            "pillow",
            "pizza",
            "podcast",
            "presentation",
            "puppy",
            "puzzle",
            "recipe",
            "release",
            "restaurant",
            "revolve",
            "rewind",
            "room",
            "run",
            "secret",
            "seed",
            "ship",
            "shirt",
            "should",
            "small",
            "spaceship",
            "stargazing",
            "skill",
            "street",
            "style",
            "sunrise",
            "taxi",
            "tidy",
            "timer",
            "together",
            "tooth",
            "tourist",
            "travel",
            "truck",
            "under",
            "useful",
            "unicorn",
            "unique",
            "uplift",
            "uniform",
            "vase",
            "violin",
            "visitor",
            "vision",
            "volume",
            "view",
            "walrus",
            "wander",
            "world",
            "winter",
            "well",
            "whirlwind",
            "x-ray",
            "xylophone",
            "yoga",
            "yogurt",
            "yoyo",
            "you",
            "year",
            "yummy",
            "zebra",
            "zigzag",
            "zoology",
            "zone",
            "zeal"
        )
    ) : GameRepository {

        private val shuffledList = originalList.map { shuffleStrategy.shuffle(it) }

        override fun shuffledWord(): String = shuffledList[index.read()]

        override fun originalWord(): String = originalList[index.read()]

        override fun next() {
            val value = index.read()
            index.save(if (value + 1 == originalList.size) 0 else value + 1)
            userInput.save("")
        }

        override fun saveUserInput(value: String) {
            userInput.save(value)
        }

        override fun userInput(): String {
            return userInput.read()
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
