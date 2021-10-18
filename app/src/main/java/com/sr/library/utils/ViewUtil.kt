package com.sr.library.utils

import android.util.Log
import android.view.View
import android.widget.ProgressBar
import com.google.android.material.snackbar.Snackbar

/**
 * Created by ramesh on 13-10-2021
 */
object ViewUtil {

    fun print(TAG : String, msg: String){
        Log.d(TAG, "$msg")
    }

    fun View.snack(msg:String){
        Snackbar.make(this,"$msg", Snackbar.LENGTH_LONG).show()
    }

    fun ProgressBar.hide(){
        this.visibility = View.GONE
    }

    fun ProgressBar.show(){
        this.visibility = View.VISIBLE
    }
}