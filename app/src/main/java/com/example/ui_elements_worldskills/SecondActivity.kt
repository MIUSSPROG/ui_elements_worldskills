package com.example.ui_elements_worldskills

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.ui_elements_worldskills.databinding.ActivitySecondBinding
import kotlin.random.Random

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val items = listOf("Material", "Design", "Components", "Android")
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, items)
        binding.compTV.setAdapter(adapter)

        binding.button.setOnClickListener {
            onGetRandomImagePressed()
        }

        binding.button.setOnLongClickListener {
            val number = Random.nextInt(100)
            val message = "Random string: $number"
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            return@setOnLongClickListener false
        }

        binding.etKeyWord.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.tvRes.text = s?.toString()
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        binding.etKeyWord.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH){
                return@setOnEditorActionListener onGetRandomImagePressed()
            }

            return@setOnEditorActionListener false
        }

    }

    private fun onGetRandomImagePressed(): Boolean{
        val keyword = binding.etKeyWord.text.toString()

        if (keyword.isBlank()){
            binding.etKeyWord.error = "Keyword is empty"
            return true
        }

        binding.pbLoadImage.visibility = View.VISIBLE
        Glide.with(this)
            .load("https://source.unsplash.com/random/800x600?$keyword")
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .circleCrop()
            .listener(object: RequestListener<Drawable>{
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    binding.pbLoadImage.visibility = View.GONE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    binding.pbLoadImage.visibility = View.GONE
                    return false
                }

            })
            .into(binding.imgvPicture)

        return false
    }
}