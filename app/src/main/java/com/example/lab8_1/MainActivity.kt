package com.example.lab8_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.lab8_1.databinding.ActivityMainBinding
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private var timer: Timer? = null
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener(){
            val tvTimer: TextView = binding.tvTimer

            if (timer != null) {
                timer?.stop()
            }

            val timer = Timer(tvTimer)
            timer.start(binding.etTime.text.toString().toInt())
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        timer?.stop()
    }
}
