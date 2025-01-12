package com.example.scrumblegame.page

import android.view.View
import androidx.annotation.StringRes
import androidx.test.espresso.matcher.BoundedMatcher
import com.google.android.material.textfield.TextInputLayout
import org.hamcrest.Description

class TextInputLayoutHasErrorText(
    private @StringRes val errorResId: Int
) : BoundedMatcher<View, TextInputLayout>(TextInputLayout::class.java) {
    override fun describeTo(description: Description) {
        description.appendText("error dosnt match with excepted $errorResId")
    }

    override fun matchesSafely(item: TextInputLayout): Boolean {
        return item.error == item.context.getString(errorResId)
    }
}