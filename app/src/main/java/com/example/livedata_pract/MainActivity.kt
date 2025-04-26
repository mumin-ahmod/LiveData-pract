package com.example.livedata_pract

import android.os.Bundle
import android.util.Log
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
import com.example.livedata_pract.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
//    private var score = 0
    private lateinit var viewModel: ScoreViewModel


    //binding 1: init Activity Binding from the layout name
    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        //binding 2: use the activity main binding inside on-create
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //instead of setContentView(R.layout.activity_main)

      // updateScoreText() // Initial update


        //1. Initialize ViewModel
        viewModel = ViewModelProvider(this)[ScoreViewModel::class.java]

        //2. Observe LiveData : score is the LiveData object we’re observing
        viewModel.score.observe(this) { score ->
//  findViewById<TextView>(R.id.scoreText).text = "Score: $score"  //old way of findViewByID
            //binding 3: new binding way:
            binding.scoreText.text = "Score: $score"

        }

        //3. Button click updates LiveData
        binding.btnIncrease.setOnClickListener {

            viewModel.increaseScore()
        }

        binding.btnClear.setOnClickListener {
            viewModel.clearScore()
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
//        binding.scoreText.text = "Score: $score"
//    }

}
