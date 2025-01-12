package com.example.scrumblegame.page

import android.view.View
import androidx.test.espresso.matcher.BoundedMatcher
import com.google.android.material.textfield.TextInputLayout
import org.hamcrest.Description

class TextInputLayoutErrorEnabledMatcher(private val exceptingEnabled: Boolean) :
    BoundedMatcher<View, TextInputLayout>(TextInputLayout::class.java) {
    override fun describeTo(description: Description) {
        description.appendText("error enabled doesnt match with excepted $exceptingEnabled")
    }

    override fun matchesSafely(item: TextInputLayout): Boolean {
        return item.isErrorEnabled == exceptingEnabled
    }
}