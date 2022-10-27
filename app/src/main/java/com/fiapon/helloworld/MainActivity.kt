package com.fiapon.helloworld

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("onCreate", "onCreate function")

        val frameLayoutButton: Button = findViewById(R.id.btnFrameLayout)
        frameLayoutButton.setOnClickListener {
            startConstraintLayout()
        }

        val saveGreetButton: Button = findViewById(R.id.btnSaveGreet)
        saveGreetButton.setOnClickListener {
            saveGreet()
        }

        val showGreetButton: Button = findViewById(R.id.btnShowGreet)
        showGreetButton.setOnClickListener {
            showGreet()
        }

        val multimediaButton: Button = findViewById(R.id.btnMultimedia)
        multimediaButton.setOnClickListener {
            showMultimedia()
        }
    }

    private fun startConstraintLayout() {
        val intent = Intent(this, ConstraintLayoutActivity::class.java)
        startActivity(intent)
    }

    private fun saveGreet() {
        val greetDict = this.getSharedPreferences("greeting", Context.MODE_PRIVATE)
        val editor = greetDict.edit()
        val editName: EditText = findViewById(R.id.editName)
        val spinner: Spinner = findViewById(R.id.spinnerGreet)

        editor.putString("name", editName.editableText.toString())
        editor.putString("greet", spinner.selectedItem.toString())
        editor.apply()

        Toast.makeText(this, "Saved new greeting successfully", Toast.LENGTH_SHORT)
    }

    private fun showGreet() {
        val intent = Intent(this, FrameLayoutActivity::class.java)
        startActivity(intent)
    }

    private fun showMultimedia() {
        val intent = Intent(this, MultimediaActivity::class.java)
        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()

        Log.i("onStart", "onStart function")
    }

    override fun onResume() {
        super.onResume()

        Log.i("onResume", "onResume function")
    }

    override fun onPause() {
        super.onPause()

        Log.i("onPause", "onPause function")
    }

    override fun onStop() {
        super.onStop()

        Log.i("onStop", "onStop function")
    }

    override fun onDestroy() {
        Log.i("onDestroy", "onDestroy function")
        super.onDestroy()
    }

    override fun onRestart() {
        super.onRestart()

        Log.i("onRestart", "onRestart function")
    }
}