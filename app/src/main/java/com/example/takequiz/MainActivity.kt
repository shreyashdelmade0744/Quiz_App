package com.example.takequiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnStart = findViewById<Button>(R.id.btnStart)
        val editText = findViewById<EditText>(R.id.editText)

        btnStart.setOnClickListener {
            if(editText.text.isEmpty()){
                Toast.makeText(this, "please enter name", Toast.LENGTH_SHORT).show()
            }
            else{
                val intent = Intent(this,QuizQuestionsActivity::class.java)
                intent.putExtra(Constants.USER_NAME,editText.text.toString())
                startActivity(intent)
                finish()
                //using finish we wouldn't come back to the previous page

            }
        }
    }
}