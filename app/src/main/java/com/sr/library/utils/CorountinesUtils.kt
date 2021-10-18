package com.sr.library.utils

/**
 * Created by ramesh on 16-06-21
 */
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object CorountinesUtils {
    private const val TAG = "CorountinesUtils"

    fun main(work : suspend(()-> Unit) ){
        CoroutineScope(Dispatchers.Main).launch {
            work()
        }
    }

}