package com.example.scrumblegame.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.scrumblegame.R
import com.example.scrumblegame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), Navigation {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
    }

    override fun navigate(screen: Screen) =
        screen.show(R.id.container, supportFragmentManager)

}

