package com.example.geoquiz

import androidx.annotation.StringRes
//See page 32 in the book for more details

//data classes must have a primary constructor
data class Question(@StringRes val textResId: Int, val answer: Boolean, var isAnswered: Boolean)
//@StringRes used to make the compiler wait and check in till the value exists and to tell the user to give string resource not hard code

