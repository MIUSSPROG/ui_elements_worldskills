package com.example.ui_elements_worldskills

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.ui_elements_worldskills.databinding.ActivityMainBinding
import org.w3c.dom.Text
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

//    private lateinit var tvHelloWorld: TextView
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding.tvHello){
            text = "Hello World"
            setTextColor(Color.GREEN)
            setBackgroundColor(Color.YELLOW)
        }

        val currentTextValue = binding.tvHello.text.toString()
        Log.d("TESTLOG", "current text value $currentTextValue")

        val textValueWithRandomNumber = "$currentTextValue ${Random.nextInt(100)}"
        binding.tvHello.text = textValueWithRandomNumber

        val updatedTextValue = binding.tvHello.text.toString()
        Log.d("TESTLOG", "Updated text value: $updatedTextValue")

        Glide
            .with(this)
//            .load(R.drawable.nature)
            .load("https://source.unsplash.com/random/800x600?space")
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .placeholder(R.drawable.ic_cloud)
            .circleCrop()
            .into(binding.simpleImgv);

//        binding.tvHello.text = "Hello World"
//        binding.tvHello.setTextColor(Color.GREEN)
//        binding.tvHello.setBackgroundColor(Color.YELLOW)

//        tvHelloWorld = findViewById(R.id.tvHello)
    }
}