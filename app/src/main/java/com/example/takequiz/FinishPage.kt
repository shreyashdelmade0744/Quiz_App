package com.example.takequiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintSet.Constraint

class FinishPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish_page)

        val username : TextView = findViewById(R.id.username)
        val score : TextView = findViewById(R.id.score)
        val btnfinish : Button = findViewById(R.id.finish_btn)


        username.text = intent.getStringExtra(Constants.USER_NAME)
        val totalquestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS,0)
        val correctanswers = intent.getIntExtra(Constants.CORRECT_ANSWERS,0)

        score.text = "your total score is $correctanswers out of $totalquestions"

        btnfinish.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))

        }

    }
}
//finally done with the project
