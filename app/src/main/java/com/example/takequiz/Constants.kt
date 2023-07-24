package com.example.takequiz

object Constants {

    const val USER_NAME : String = "user_name"
    const val TOTAL_QUESTIONS : String = "total_questions"
    const val CORRECT_ANSWERS : String = "correct_answers"


    fun getQuestions():ArrayList<Question>{
        val questionsList = ArrayList<Question>()

        val que1 = Question(
            1,"Identify this cartoon ",R.drawable.panda,
            "Panda","Pickachu","Shinchan","Bulbasaur",
            1
        )
        questionsList.add(que1)

        val que2 = Question(
            1,"Identify this cartoon ",R.drawable.ash,
            "Panda","ash","Shinchan","Bulbasaur",
            2
        )
        questionsList.add(que2)

        val que3 = Question(
            1,"Identify this cartoon ",R.drawable.picka,
            "Panda","Pikachu","Shinchan","Bulbasaur",
            2
        )
        questionsList.add(que3)

        val que4 = Question(
            1,"Identify this cartoon ",R.drawable.dore,
            "Panda","Pickachu","dora","Bulbasaur",
            3
        )
        questionsList.add(que4)



        return questionsList


    }
}