package com.example.genericrecyclerlist.ui

import android.os.Handler
import android.os.Looper

object Utils {
    var retryTimes: Int = 0
    private var handler: Handler = Handler(Looper.getMainLooper())
    private var isRunning = false

    private var runnable: Runnable = object: Runnable {
        override fun run() {
            retryTimes += 1
            if (retryTimes >= 20) {
                handler.removeCallbacks(this)
            } else {
                handler.postDelayed(this, 1000)
            }
        }
    }

    fun startTimer() {
        if (!isRunning) {
            isRunning = true
            handler.post(runnable)
        }
    }
}