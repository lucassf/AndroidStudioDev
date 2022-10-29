package com.fiapon.helloworld

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.core.app.NotificationCompat
import com.fiapon.helloworld.multimedia.GoogleMapsActivity
import kotlinx.android.synthetic.main.activity_main.*

const val PRIMARY_CHANNEL = "main_channel"
const val PRIMARY_NOTIFICATION_ID = 100

class MainActivity : AppCompatActivity() {

    lateinit var notifyManager: NotificationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("onCreate", "onCreate function")

        btnSaveGreet.setOnClickListener { saveGreet() }
        btnShowGreet.setOnClickListener { showGreet() }
        btnMultimedia.setOnClickListener { showMultimedia() }
        btnNotification.setOnClickListener { sendNotification() }

        createNotificationChannel()
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
        val intent = Intent(this, RetrofitLayoutActivity::class.java)
        startActivity(intent)
    }

    private fun showMultimedia() {
        val intent = Intent(this, MultimediaActivity::class.java)
        startActivity(intent)
    }

    private fun sendNotification() {
        val builder = getNotificationBuilder()
        notifyManager.notify(PRIMARY_NOTIFICATION_ID, builder.build())
    }

    private fun createNotificationChannel() {
        notifyManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                PRIMARY_CHANNEL, "basic_notification", NotificationManager.IMPORTANCE_HIGH
            )
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.enableVibration(true)
            notificationChannel.description = "The first notification ever"

            notifyManager.createNotificationChannel(notificationChannel)
        } else {
            Toast.makeText(
                this, "Android version does not support notifications (?)", Toast.LENGTH_SHORT
            )
        }
    }

    private fun getNotificationBuilder(): NotificationCompat.Builder {
        val notificationIntent = Intent(this, GoogleMapsActivity::class.java)

        val builder = NotificationCompat.Builder(this, PRIMARY_CHANNEL)
        builder.setContentTitle("You just won 0.011ts!!")
        builder.setContentText("Begone thot\nWelcome to special skeleton challenge week")
        builder.setSmallIcon(R.drawable.ic_stat_name)
        builder.priority = NotificationCompat.PRIORITY_HIGH
        builder.setContentIntent(
            PendingIntent.getActivity(
                this, 0, notificationIntent, 0
            )
        )

        return builder
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