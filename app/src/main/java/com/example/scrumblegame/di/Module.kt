package com.example.scrumblegame.di


interface Module<T : MyViewModel> {
    fun viewModel(): T
}