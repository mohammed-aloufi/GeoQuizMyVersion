package com.example.geoquiz

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalStateException

//LESSON 1: Your First Android Application (page: 1)
//Lesson 2: Android and Model-View-Controller

//good practice to write all const variables name in CAP
////Log tag, because it is a const, i wrote it here
private const val TAG = "MainActivity" //the name of this class which is best practice
private const val KEY_INDEX = "index" //save bundle key
const val EXTRA_IS_TRUE_OR_NOT =
    "MainActivity_Question_Answer" //good practice to write this class name first
const val EXTRA_QUESTION_TEXT = "MainActivity_Question"

private const val REQUAST_CODE_CHEAT = 0

class MainActivity : AppCompatActivity() {
    //Widgets "Views"
    private lateinit var questionTextView: TextView
    private lateinit var scoreTextView: TextView
    private lateinit var falseButton: Button
    private lateinit var trueButton: Button
    private lateinit var nextButton: ImageButton
    private lateinit var previousButton: ImageButton
    private lateinit var cheatButton: Button

    //How to make instance of ViewModel
    //Note:- "this" means this class 'MainActivity', because ViewModelProvider needs to know where he is
    //Note:- "ViewModelProvider" is the manger of "QuizViewModel"
    private val quizViewModel by lazy {
        ViewModelProvider(this).get(QuizViewModel::class.java)
    } //"QuizViewModel::class.java" to point to it

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setting this view "MainActivity.kt" with the "activity_main.xml" design
        setContentView(R.layout.activity_main)

//        Log.d(TAG, "onCreate()")
//        Log.d(TAG, "Hi, I'm view model from MainActivity $quizViewModel")

        //getting the value in the bundle
        val currentIndex = savedInstanceState?.getInt(KEY_INDEX) ?: 0
        Log.d(TAG, "bundle value : $currentIndex")
        quizViewModel.currentIndex = currentIndex

        //linking the widget "activity_main.xml" with this class "MainActivity.kt" using their 'id'
        questionTextView = findViewById(R.id.questionTextView)
        scoreTextView = findViewById(R.id.scoreTextView)
        trueButton = findViewById(R.id.trueButton)
        falseButton = findViewById(R.id.falseButton)
        nextButton = findViewById(R.id.nextQuestion)
        previousButton = findViewById(R.id.previousQuestion)
        cheatButton = findViewById(R.id.cheatButton)

        scoreTextView.setText("${quizViewModel.score}/10")

        trueButton.setOnClickListener {
            checkAnswer(true)

        }
        falseButton.setOnClickListener {
            checkAnswer(false)
        }

        questionTextView.setOnClickListener {
            quizViewModel.nextQuestion()
            updateQuestion()
        }

        nextButton.setOnClickListener {
            quizViewModel.nextQuestion()
            updateQuestion()
        }

        previousButton.setOnClickListener {
            quizViewModel.previousQuestion()
            updateQuestion()
        }

        cheatButton.setOnClickListener { view ->
            //creating an intent (this class, the destination class)
            val intent = Intent(this, CheatActivity::class.java)

            //sending the answer to CheatActivity
            intent.putExtra(EXTRA_IS_TRUE_OR_NOT, quizViewModel.currentQuestionAnswer)
            intent.putExtra(EXTRA_QUESTION_TEXT, quizViewModel.currentQuestionText)
            //starting the activity
//            startActivity(intent)

            //book page 117 explains it
            val options = ActivityOptions
                .makeClipRevealAnimation(view, 0, 0, view.width, view.height)
            //if we want to send result and will get result from the other class we use
            //this fun is deprecated ckeck android luncher
            startActivityForResult(intent, REQUAST_CODE_CHEAT, options.toBundle())
        }

        updateQuestion()
    }

    //Save bundle
    //you should only store light data in bundle
    //similar to mapOf(key, value)
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "a value has been saved")
        //key must be val and const
        outState.putInt(KEY_INDEX, quizViewModel.currentIndex)

    }

    //this fun would be called if we used setResult in the other class "CheatActivity"
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //if the result is not OK return "exit the function"
        if (resultCode != Activity.RESULT_OK){
            return
        }

        if (requestCode == REQUAST_CODE_CHEAT){
            quizViewModel.isCheater = data?.getBooleanExtra(EXTRA_ANSWER_SHOWN, false) ?: false
        }
    }

    private fun updateQuestion() {
        val questionTextRedId = quizViewModel.currentQuestionText
        questionTextView.setText(questionTextRedId)
        isQuestionAnswered()
    }

    private fun isQuestionAnswered() {
        trueButton.isEnabled = !quizViewModel.isCurrentQuestionAnswered
        falseButton.isEnabled = !quizViewModel.isCurrentQuestionAnswered
    }

    private fun checkAnswer(userAnswer: Boolean) {
        //this called a 'flag' to debug
        //used to debug for "misbehavior errors'
//        Log.d(TAG, "I'm from checkAnswer: ", IllegalStateException())
        //Or you can use breakpoint
        val correctAnswer = quizViewModel.currentQuestionAnswer
        val toastMessage: Int

        if (quizViewModel.isCheater){
            toastMessage = R.string.cheaterToast
        }else if (userAnswer == correctAnswer){
            toastMessage = R.string.correct_toast
            questionBeenAnswered()
            updateScore()
        }else {
            toastMessage = R.string.Incorrect_toast
            questionBeenAnswered()
        }
        Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show()
    }

    private fun questionBeenAnswered(){
        quizViewModel.isCurrentQuestionAnswered = true
        isQuestionAnswered()
    }

    private fun updateScore() {
        quizViewModel.score += 1
        scoreTextView.setText("${quizViewModel.score}/10")
    }
}


////LIFECYCLE
//override fun onStart() {
//    super.onStart()
//    Log.d(TAG, "onStart()")
//}
//
//override fun onResume() {
//    super.onResume()
//    Log.d(TAG, "onResume()")
//}
//
//override fun onStop() {
//    super.onStop()
//    Log.d(TAG, "onStop()")
//}
//
//override fun onRestart() {
//    super.onRestart()
//    Log.d(TAG, "onRestart()")
//}
//
//override fun onPause() {
//    super.onPause()
//    Log.d(TAG, "onPause()")
//}
//
//override fun onDestroy() {
//    super.onDestroy()
//    Log.d(TAG, "onDestroy()")
//}