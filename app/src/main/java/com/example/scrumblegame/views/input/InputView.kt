package com.example.scrumblegame.views.input

import android.content.Context
import android.os.Parcelable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.scrumblegame.R
import com.example.scrumblegame.databinding.InputBinding
import com.example.scrumblegame.game.InputUiState

class InputView : FrameLayout, UpdateInput {

    private lateinit var state: InputUiState

    private val binding = InputBinding.inflate(LayoutInflater.from(context), this, true)

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun onSaveInstanceState(): Parcelable? {
        return super.onSaveInstanceState()?.let {
            val savedState = InputViewSavedState(it)
            savedState.save(state)
            return savedState
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val restoredState = state as InputViewSavedState
        super.onRestoreInstanceState(restoredState.superState)
        update(restoredState.restore())
    }

    override fun update(uiState: InputUiState) {
        state = uiState
        state.update(this)
    }

    override fun update(userInput: String) {
        binding.inputEditText.setText(userInput)
    }

    override fun update(errorIsEnabled: Boolean, enabled: Boolean) = with(binding) {
        inputLayout.isErrorEnabled = errorIsEnabled
        if (errorIsEnabled)
            inputLayout.error = inputLayout.context.getString(R.string.incorrect_message)
        inputLayout.isEnabled = enabled
    }

    fun addTextChangedListener(textWatcher: TextWatcher) {
        binding.inputEditText.addTextChangedListener(textWatcher)
    }

    fun removeTextChangedListener(textWatcher: TextWatcher) {
        binding.inputEditText.removeTextChangedListener(textWatcher)
    }

    fun text(): String {
        return binding.inputEditText.text.toString()
    }
}

interface UpdateInput {

    fun update(uiState: InputUiState)

    fun update(userInput: String)

    fun update(errorIsEnabled: Boolean, enabled: Boolean)
}