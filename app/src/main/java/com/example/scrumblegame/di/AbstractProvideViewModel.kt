package com.example.scrumblegame.di


abstract class AbstractProvideViewModel(
    protected val core: Core,
    private val nextChain: ProvideViewModel,
    private val viewModelClass: Class<out MyViewModel>
) : ProvideViewModel {

    override fun <T : MyViewModel> makeViewModel(clazz: Class<T>): T {
        return if (clazz == viewModelClass)
            module().viewModel() as T
        else
            return nextChain.makeViewModel(clazz)

    }

    protected abstract fun module(): Module<*>
}