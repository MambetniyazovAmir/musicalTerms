package com.musicalTerms

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager


object Helper {
    fun showKeyboard(activity: Activity) {
        val imm: InputMethodManager =
            activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (imm != null) {
            imm.showSoftInput(activity.currentFocus, InputMethodManager.SHOW_IMPLICIT)
        }
    }

    fun hideKeyboard(activity: Activity) {
        val imm: InputMethodManager =
            activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (imm != null && activity.currentFocus != null) {
            imm.hideSoftInputFromWindow(activity.currentFocus!!.windowToken, 0)
        }
    }
}