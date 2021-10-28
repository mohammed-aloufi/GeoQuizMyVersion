package com.example.geoquiz

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
private const val TAG = "CheatActivity"
const val EXTRA_ANSWER_SHOWN = "cheater"

class CheatActivity : AppCompatActivity() {

    //Widgets
    private  lateinit var questionTextView: TextView
    private lateinit var answerTextView: TextView
    private lateinit var showAnswerButton: Button

    //Attributes
    var questionText = 0
    var answerIsTrueOrFalse = false //default value

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)

        //receiving the data that were sent from MainActivity "the answer"
        questionText = intent.getIntExtra(EXTRA_QUESTION_TEXT, R.string.warning_message)
        answerIsTrueOrFalse = intent.getBooleanExtra(EXTRA_IS_TRUE_OR_NOT, false)

        questionTextView = findViewById(R.id.questionTextView)
        answerTextView = findViewById(R.id.answerTextView)
        showAnswerButton = findViewById(R.id.showAnswerButton)

        questionTextView.setText(questionText)
        showAnswerButton.setOnClickListener {
            answerTextView.text = answerIsTrueOrFalse.toString().uppercase()
            setAnswerShownResult()
        }
    }

    fun setAnswerShownResult(){
        //data would put extra if showAnswerButton is pressed
        val data = Intent().apply {
            putExtra(EXTRA_ANSWER_SHOWN, true)
        }
        //then we setResult with REQUAST_CODE_CHEAT that's in MainActivity and data
        setResult(Activity.RESULT_OK, data)
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