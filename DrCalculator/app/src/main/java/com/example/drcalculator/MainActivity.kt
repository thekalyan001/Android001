package com.example.drcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //Let's display the text of given input by appending the text as we want to display all text
        //not want o replace text so use append
        btn_ac.setOnClickListener{
            inputText.text=""
            outputText.text=""
        }
        btn_0.setOnClickListener {
            inputText.append("0")
        }
        btn_1.setOnClickListener {
            inputText.append("1")
        }
        btn_2.setOnClickListener {
            inputText.append("2")
        }
        btn_3.setOnClickListener {
            inputText.append("3")
        }
        btn_4.setOnClickListener {
            inputText.append("4")
        }
        btn_5.setOnClickListener {
            inputText.append("5")
        }
        btn_6.setOnClickListener {
            inputText.append("6")
        }
        btn_7.setOnClickListener {
            inputText.append("7")
        }
        btn_8.setOnClickListener {
            inputText.append("8")
        }
        btn_9.setOnClickListener {
            inputText.append("9")
        }
        btn_dot.setOnClickListener {
            inputText.append(".")
        }
        btn_plus.setOnClickListener {
            inputText.append(" + ")
        }
        btn_minus.setOnClickListener {
            inputText.append(" - ")
        }
        btn_divide.setOnClickListener {
            inputText.append(" / ")
        }
        btn_multiplication.setOnClickListener {
            inputText.append(" * ")
        }
        btn_modulo.setOnClickListener {
            inputText.append(" % ")
        }

        btn_cutLast.setOnClickListener{
                val expression = inputText.text.toString()
                if (expression.isNotEmpty()) {
                    inputText.text = expression.substring(0, expression.length - 1)
                }
        }

        //by the help of expression builder we wi'll eveluate the expression
        btn_equal.setOnClickListener {
            val expression = ExpressionBuilder(inputText.text.toString()).build()
            val result = expression.evaluate()
            val longresult = result.toDouble() //agar hamara long result ho

            if(result == longresult.toDouble()) //agar hamara result dot wala hai
            {
                outputText.text=longresult.toString()
            }
            else //hamara result bina dot wala hai
            {
                outputText.text=result.toString()
            }
        }
    }
}

/*ye hai data binding ka
https://developer.android.com/topic/libraries/view-binding#activities

 apan bind view binding ke bna rhe hain synthetic binding ke

 see this is very good difference between ViewBinding and DataBinding
 https://medium.com/@hardianbobby/databinding-vs-viewbinding-simple-comparison-792fa8d72e8
 https://stackoverflow.com/questions/58040778/android-difference-between-databinding-and-viewbinding
 */