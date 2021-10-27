package com.example.geoquiz

import android.util.Log
import androidx.lifecycle.ViewModel

//Log tag
private const val TAG = "QuizViewModel"

//fixing the rotation shutdown bug using this
//ViewModel is responsible for writing and deleting data in the memory
//any data that you put in the view model won't get cleared in till the user clears it
//check page 68
class QuizViewModel : ViewModel() {

    //Attributes
    private val questionBank = listOf(
        //using the model class Question to create objects
        Question(R.string.firstQuestion, false, false),
        Question(R.string.secondQuestion, false, false),
        Question(R.string.thirdQuestion, true, false),
        Question(R.string.fourthQuestion, true, false),
        Question(R.string.fifthQuestion, true, false),
        Question(R.string.sixthQuestion, true, false),
        Question(R.string.seventhQuestion, false, false),
        Question(R.string.eighthQuestion, true, false),
        Question(R.string.ninthQuestion, false, false),
        Question(R.string.tenthQuestion, true, false)
    )

    var currentIndex = 0
    var score = 0
    val currentQuestionText: Int
            get() = questionBank[currentIndex].textResId
    val currentQuestionAnswer: Boolean
            get() = questionBank[currentIndex].answer

    var isCurrentQuestionAnswered: Boolean
            get() = questionBank[currentIndex].isAnswered
            set(value) {
                questionBank[currentIndex].isAnswered = value
            }

    fun nextQuestion(){
        currentIndex =
            (currentIndex + 1) % questionBank.size //Setting the highest range that currentIndex can be
    }

    fun previousQuestion(){
        if (currentIndex > 0){
            currentIndex = (currentIndex - 1)
        } else if (currentIndex == 0){
            currentIndex = 0
        }
    }
}