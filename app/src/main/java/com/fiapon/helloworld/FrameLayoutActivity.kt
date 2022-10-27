package com.fiapon.helloworld

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class FrameLayoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frame_layout)

        val greetDict = this.getSharedPreferences("greeting", Context.MODE_PRIVATE)
        val name = greetDict.getString("name", "")
        val greeting = greetDict.getString("greet", "")

        val greetText: TextView = findViewById(R.id.txtUser)
        if (name == null || name.isEmpty())
            greetText.text = "Anonymous"
        else if (greeting == "None")
            greetText.text = name
        else
            greetText.text = "$greeting $name"
    }
}
