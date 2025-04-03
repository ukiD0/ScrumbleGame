package com.example.scrumblegame.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.scrumblegame.R
import com.example.scrumblegame.di.MyViewModel
import com.example.scrumblegame.di.ProvideViewModel

class MainActivity : AppCompatActivity(), Navigation, ProvideViewModel {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null)
            navigateToGame()
    }

    override fun navigate(screen: Screen) =
        screen.show(R.id.container, supportFragmentManager)

    override fun <T : MyViewModel> makeViewModel(clazz: Class<T>): T =
        (application as ProvideViewModel).makeViewModel(clazz)


}

