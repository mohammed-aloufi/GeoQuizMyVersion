package com.example.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
private const val TAG = "CheatActivity"
class CheatActivity : AppCompatActivity() {

    //Widgets
    private lateinit var answerTextView: TextView
    private lateinit var showAnswerButton: Button

    //Attributes
    var answerIsTrueOrFalse = false //default value

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)

        //receiving the data that were sent from MainActivity "the answer"
        answerIsTrueOrFalse = intent.getBooleanExtra(EXTRA_IS_TRUE_OR_NOT, false)

        answerTextView = findViewById(R.id.answerTextView)
        showAnswerButton = findViewById(R.id.showAnswerButton)

        showAnswerButton.setOnClickListener {
            answerTextView.text = answerIsTrueOrFalse.toString().uppercase()
        }
    }

    //LIFECYCLE
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume()")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop()")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart()")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy()")
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