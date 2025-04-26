package com.example.aplikacjemobilne

import androidx.lifecycle.ViewModel

import android.os.CountDownTimer

class TimerViewModel : ViewModel() {
    var timeLeftInMillis: Long = 60000
    var isRunning = false
    var timer: CountDownTimer? = null
}

