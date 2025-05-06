package com.example.drinkbarapp.viewModel

import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TimerViewModel : ViewModel() {
    private val _timeLeft = MutableStateFlow("01:00") // tekst do wyświetlania
    val timeLeft: StateFlow<String> = _timeLeft

    private val _isRunning = MutableStateFlow(false)
    val isRunning: StateFlow<Boolean> = _isRunning

    private var timer: CountDownTimer? = null
    private var timeLeftInMillis: Long = 60000L // 1 minuta

    fun startTimer() {
        timer?.cancel()

        timer = object : CountDownTimer(timeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                _timeLeft.value = formatTime(millisUntilFinished)
            }

            override fun onFinish() {
                _isRunning.value = false
                _timeLeft.value = "00:00"
            }
        }.start()

        _isRunning.value = true
    }

    fun stopTimer() {
        timer?.cancel()
        _isRunning.value = false
    }

    fun resetTimer() {
        timer?.cancel()
        timeLeftInMillis = 60000L
        _timeLeft.value = formatTime(timeLeftInMillis)
        _isRunning.value = false
    }

    private fun formatTime(millis: Long): String {
        val minutes = (millis / 1000) / 60
        val seconds = (millis / 1000) % 60
        return String.format("%02d:%02d", minutes, seconds)
    }

    fun setInitialTime(seconds: Long) {
        this.timeLeftInMillis = seconds * 1000
        _timeLeft.value = formatTime(timeLeftInMillis)
    }
}