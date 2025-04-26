package com.example.livedata_pract

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class ScoreViewModel : ViewModel() {
    // MutableLiveData: Can be changed //1. declare the state
    private val _score = MutableLiveData<Int>(0) // Backing property

    // LiveData: Read-only for UI //2. create a read only version of the state
    val score: LiveData<Int> = _score


    //takes initial score and updates //3. business logic for ui in viewmodel
    fun increaseScore() {
        _score.value = (_score.value ?: 0) + 1
    }

    fun clearScore() {
        _score.value = 0
    }
}