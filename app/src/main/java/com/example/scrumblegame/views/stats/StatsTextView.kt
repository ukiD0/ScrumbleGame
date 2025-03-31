package com.example.scrumblegame.views.stats

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import com.example.scrumblegame.R

class StatsTextView : androidx.appcompat.widget.AppCompatTextView, UpdateStats {

    private lateinit var state: StatsUiState

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun onSaveInstanceState(): Parcelable? {
        return super.onSaveInstanceState()?.let {
            val savedState = StatsSavedState(it)
            savedState.save(state)
            return savedState
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val restoredState = state as StatsSavedState
        super.onRestoreInstanceState(restoredState.superState)
        update(restoredState.restore())

    }

    override fun update(outer: StatsUiState) {
        state = outer
        state.show(this)
    }

    override fun update(skips: Int, fails: Int, corrects: Int) {
        text = resources.getString(R.string.stats, skips, fails, corrects)
    }
}

interface UpdateStats {

    fun update(outer: StatsUiState)

    fun update(skips: Int, fails: Int, corrects: Int)
}