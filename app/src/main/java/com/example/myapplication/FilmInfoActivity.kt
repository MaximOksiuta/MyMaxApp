package com.example.myapplication

import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.myapplication.databinding.ActivityFilmInfoBinding

class FilmInfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFilmInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFilmInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tvTitle.text = intent.getStringExtra("title").toString()
        val rating = intent.getIntExtra("rating", 0)
        binding.pbRating.progress = rating
        binding.tvRating.text = "${rating}%"
        with(binding.pbRating) {
            if (rating >= 80) {
                progressTintList = ColorStateList.valueOf(getColor(R.color.green_bright))
            } else if (rating >= 50) {
                progressTintList = ColorStateList.valueOf(getColor(R.color.yellow_bright))
            } else {
                progressTintList = ColorStateList.valueOf(getColor(R.color.red_bright))
            }
        }
        binding.tvDescription.text = intent.getStringExtra("description").toString()
        binding.pbLoadImage.visibility = View.VISIBLE
        Glide.with(this).load(intent.getStringExtra("imageURL").toString())
            .listener(object : RequestListener<Drawable> {
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
            }).into(binding.ivImage)
    }
}