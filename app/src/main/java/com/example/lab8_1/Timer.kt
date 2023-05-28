package com.example.lab8_1

import android.media.MediaPlayer
import android.util.Log
import android.widget.TextView
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

class Timer(private val textView: TextView) {
    private lateinit var timer : Disposable
    private var mediaPlayer: MediaPlayer? = null
    private var isTimerRunning: Boolean = false

    fun start(count: Int){
        if (!isTimerRunning) {
        var remainingSeconds = count + 1
            isTimerRunning = true
        timer = Flowable.interval(1, TimeUnit.SECONDS)
            .take(count.toLong() + 1)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { second ->
                    remainingSeconds -= 1
                    textView.text = formatTime(remainingSeconds)
                },
                { error ->
                    textView.text = "Помилка: ${error.message}"
                },
                {
                    textView.text = "Таймер завершено"
                    playAudioNotification()
                }
            )
        }
    }

    fun stop(){
        timer.dispose()
        isTimerRunning = false
    }

    private fun formatTime(seconds: Int): String {
        val minutes = seconds / 60
        val remainingSeconds = seconds % 60
        return String.format("%02d:%02d", minutes, remainingSeconds)
    }

    private fun playAudioNotification() {
        mediaPlayer = MediaPlayer.create(textView.context, R.raw.notification)
        mediaPlayer?.start()
    }
}
