package com.example.whosaidmeow

import android.graphics.Color
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private var mp = MediaPlayer()
    private var number = 0
    private var lostNumber = 0
    private var total = 0
    private var count = 0
    private var roundEnd = false
    private val list = listOf(R.raw.cat, R.raw.chicken, R.raw.cow, R.raw.dog, R.raw.duck, R.raw.sheep)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        play(list[number])

        imageViewCat.setOnClickListener { checkAnswer(0) }
        imageViewChicken.setOnClickListener { checkAnswer(1) }
        imageViewCow.setOnClickListener { checkAnswer(2) }
        imageViewDog.setOnClickListener { checkAnswer(3) }
        imageViewDuck.setOnClickListener { checkAnswer(4) }
        imageViewSheep.setOnClickListener { checkAnswer(5) }
        buttonNext.setOnClickListener {
            roundEnd = false
            total++
            textViewCount.setTextColor(Color.BLACK)
            textViewCount.text = "Вы набрали $count из $total!"
            lostNumber = number
            while (number == lostNumber) {
                number = Random.nextInt(list.size)
            }
            play(list[number])
        }
    }

    private fun checkAnswer(answer: Int) {
        if (!roundEnd) {
            if (number == answer) {
                textViewCount.setTextColor(Color.GREEN)
                textViewCount.text = "Правильно!"
                count++
            } else {
                textViewCount.setTextColor(Color.RED)
                textViewCount.text =  "Не угадал..."
            }
            roundEnd = true
            play(list[answer])
        }
    }

    private fun play(raw: Int) {
        mp.release()
        mp = MediaPlayer.create(baseContext, raw)
        mp.start()
    }

    override fun onStop() {
        super.onStop()

        mp.release()
    }
}