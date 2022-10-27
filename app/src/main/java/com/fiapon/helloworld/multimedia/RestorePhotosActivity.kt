package com.fiapon.helloworld.multimedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.fiapon.helloworld.R
import kotlinx.android.synthetic.main.activity_restore_photos.*

class RestorePhotosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restore_photos)

        val url =
            "https://static.scientificamerican.com/sciam/cache/file/32665E6F-8D90-4567-9769D59E11DB7F26_source.jpg?w=590&h=800&7E4B4CAD-CAE1-4726-93D6A160C2B068B2"
        val url2 =
            "https://icatcare.org/app/uploads/2018/07/Thinking-of-getting-a-cat.png"

        Glide.with(this).load(url).into(imageGlide)
        Glide.with(this).load(url2).into(imageGlide2)
    }
}