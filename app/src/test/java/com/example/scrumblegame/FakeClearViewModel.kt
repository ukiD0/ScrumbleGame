package com.example.scrumblegame

import com.example.scrumblegame.di.ClearViewModel
import com.example.scrumblegame.di.MyViewModel

class FakeClearViewModel : ClearViewModel {

    var clasz: Class<out MyViewModel> = FakeViewModel::class.java

    override fun clear(viewModelClass: Class<out MyViewModel>) {
        clasz = viewModelClass
    }
}

private class FakeViewModel : MyViewModel