package com.bignerdranch.android.servicetest

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.*

class MyService : Service() {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onCreate() {
        super.onCreate()
        log("onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        log("onStartCommand")
        coroutineScope.launch {
            for (i in 0 until 100) {
                delay(1000)
                log("Таймер- $i")
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel()
        log("onDestroy")
    }

    private fun log(message: String) {
        Log.d("MyService", "MyService: $message")
    }

    companion object {
        fun newInstance(context: Context): Intent {
            return Intent(context, MyService::class.java)
        }
    }
}