package com.example.takequiz

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class QuizQuestionsActivity : AppCompatActivity() ,View.OnClickListener{

    private var mCurrentPosition : Int = 1
    lateinit var mQuestionsList : ArrayList<Question>
    private var mSelectedOptionPosition : Int = 0
    lateinit var musername : String
    var mcorrectanswers : Int = 0

    lateinit var progressbar : ProgressBar
    lateinit var numbering : TextView
    lateinit var tvImage : ImageView
    lateinit var tvQuestion : TextView
    lateinit var submit : Button

    lateinit var option_1 : TextView
    lateinit var option_2 : TextView
    lateinit var option_3 : TextView
    lateinit var option_4 : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        musername = intent.getStringExtra(Constants.USER_NAME).toString()



        progressbar = findViewById(R.id.progress_bar)
        numbering=findViewById(R.id.numbering)
        tvImage = findViewById(R.id.tv_image)
        tvQuestion = findViewById(R.id.tv_questions)
        submit = findViewById(R.id.sub_but)


        option_1=findViewById(R.id.option_1)
        option_2=findViewById(R.id.option_2)
        option_3=findViewById(R.id.option_3)
        option_4=findViewById(R.id.option_4)

        option_1.setOnClickListener(this)
        option_2.setOnClickListener(this)
        option_3.setOnClickListener(this)
        option_4.setOnClickListener(this)
        submit.setOnClickListener(this)


        mQuestionsList = Constants.getQuestions()
//        defaultOptionsView()

        setQuestion()
        defaultOptionsView()


    }

    private fun setQuestion() {

        defaultOptionsView()
        var question: Question = mQuestionsList[mCurrentPosition- 1]
        progressbar.progress = mCurrentPosition
        numbering.text = "$mCurrentPosition/${progressbar.max}"
        tvQuestion.text = question.question
        option_1.text = question.OptionOne
        option_2.text = question.OptionTwo
        option_3.text = question.OptionThree
        option_4.text = question.OptionFour
        tvImage.setImageResource(question.image)

        if(mCurrentPosition == mQuestionsList.size){
            submit.text="FINISH"
        }
        else{
            submit.text = "SUBMIT"

        }
    }

    private fun defaultOptionsView(){
        val options = ArrayList<TextView>()
        if(option_1!=null) {
            options.add(0,option_1)
        }
        if(option_2!=null) {
            options.add(1,option_2)
        }
        if(option_3!=null) {
            options.add(2,option_3)
        }
        if(option_4!=null) {
            options.add(3,option_4)
        }
        for(i in options){
            i.setTextColor(Color.parseColor("#808080"))
            i.typeface = Typeface.DEFAULT
            i.background = ContextCompat.getDrawable(this,R.drawable.default_option_border_bg)

        }
    }

    private fun selectedOptionView(tv : TextView, selectedOption : Int){
        mSelectedOptionPosition = selectedOption
        defaultOptionsView()

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface , Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this,R.drawable.selected_option)
    }


    override fun onClick(view: View?) {
        when(view?.id){
            R.id.option_1 -> {selectedOptionView(option_1,1)}
            R.id.option_2 -> {selectedOptionView(option_2,2)}
            R.id.option_3 -> {selectedOptionView(option_3,3)}
            R.id.option_4 -> {selectedOptionView(option_4,4)}
            R.id.sub_but -> {
            if(mSelectedOptionPosition == 0){
                mCurrentPosition++
                when{
                    mCurrentPosition <= mQuestionsList.size ->{
                        setQuestion()
                    }
                    else -> {
                        val intent = Intent(this,FinishPage::class.java)
                        intent.putExtra(Constants.USER_NAME,musername)
                        intent.putExtra(Constants.CORRECT_ANSWERS,mcorrectanswers)
                        intent.putExtra(Constants.TOTAL_QUESTIONS,mQuestionsList.size)
                        startActivity(intent)
                        finish()
                    }
                }
            }
                else{
                    val question = mQuestionsList[mCurrentPosition-1]
                if(question.correctAnswer != mSelectedOptionPosition){
                    answerView(mSelectedOptionPosition,R.drawable.wrong_option_border_bg)
                }
                else{
                    mcorrectanswers++
                }
                answerView(question.correctAnswer , R.drawable.correct_option_border_bg)
                if(mCurrentPosition == mQuestionsList.size){
                    submit.text="FINISH"
                }
                else{
                    submit.text = "Next Question"
            }
                mSelectedOptionPosition = 0
            }

    }
}}
        fun answerView(answer : Int , drawableView : Int){
        when(answer){
            1-> { option_1.background = ContextCompat.getDrawable(this@QuizQuestionsActivity,drawableView)}
            2-> { option_2.background = ContextCompat.getDrawable(this@QuizQuestionsActivity,drawableView)}
            3-> { option_3.background = ContextCompat.getDrawable(this@QuizQuestionsActivity,drawableView)}
            4-> { option_4.background = ContextCompat.getDrawable(this@QuizQuestionsActivity,drawableView)}
        }


    }
}