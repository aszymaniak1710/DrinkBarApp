package com.example.aplikacjemobilne

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

import android.util.Log

import androidx.fragment.app.activityViewModels

class TimerFragment : Fragment() {

    private val viewModel: TimerViewModel by activityViewModels()

    private lateinit var timerText: TextView
    private lateinit var startButton: Button
    private lateinit var stopButton: Button
    private lateinit var resetButton: Button

    private var countDownTimer: CountDownTimer? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.timer_fragment, container, false)

        timerText = view.findViewById(R.id.timer_text)
        startButton = view.findViewById(R.id.start_button)
        stopButton = view.findViewById(R.id.stop_button)
        resetButton = view.findViewById(R.id.reset_button)

        updateTimerText()

        if (viewModel.isRunning) {
            startTimer()
        }

        startButton.setOnClickListener {
            if (!viewModel.isRunning) startTimer()
        }

        stopButton.setOnClickListener {
            stopTimer()
        }

        resetButton.setOnClickListener {
            stopTimer()
            viewModel.timeLeftInMillis = 60000
            updateTimerText()
        }

        return view
    }

    private fun startTimer() {

        viewModel.timer?.cancel()

        viewModel.timer = object : CountDownTimer(viewModel.timeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                viewModel.timeLeftInMillis = millisUntilFinished
                updateTimerText()
            }

            override fun onFinish() {
                viewModel.isRunning = false
                countDownTimer = null
            }
        }.start()

        viewModel.isRunning = true
    }


    private fun stopTimer() {
        viewModel.timer?.cancel()
        viewModel.isRunning = false
    }

    private fun updateTimerText() {
        val minutes = (viewModel.timeLeftInMillis / 1000) / 60
        val seconds = (viewModel.timeLeftInMillis / 1000) % 60
        timerText.text = String.format("%02d:%02d", minutes, seconds)
    }
}
