package com.example.livedata_pract

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
//    private var score = 0
    private lateinit var viewModel: ScoreViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

//        updateScoreText() // Initial update


        //1. Initialize ViewModel
        viewModel = ViewModelProvider(this)[ScoreViewModel::class.java]

        //2. Observe LiveData : score is the LiveData object we’re observing
        viewModel.score.observe(this) { score ->
            findViewById<TextView>(R.id.scoreText).text = "Score: $score"
        }

        //3. Button click updates LiveData
        findViewById<Button>(R.id.btnIncrease).setOnClickListener {
            viewModel.increaseScore()
        }


        //boilerplate to take fullscreen - ignore
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


    }

//    fun increaseScore(view: View) {
//        score++
//        updateScoreText() // Must call manually when: Without using live data
//    }

//    private fun updateScoreText() {
//        findViewById<TextView>(R.id.scoreText).text = "Score: $score"
//    }

}
