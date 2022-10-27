package com.fiapon.helloworld

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.fiapon.helloworld.multimedia.*

class MultimediaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multimedia)
    }

    fun accessPhotos(view: View) {
        val intent = Intent(this, RestorePhotosActivity::class.java)
        startActivity(intent)
    }

    fun accessMaps(view: View) {
        val intent = Intent(this, GoogleMapsActivity::class.java)
        startActivity(intent)
    }

    fun accessWebView(view: View) {
        val intent = Intent(this, WebViewActivity::class.java)
        startActivity(intent)
    }
}