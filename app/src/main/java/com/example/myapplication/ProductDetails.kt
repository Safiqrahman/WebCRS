package com.example.myapplication

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.material.appbar.MaterialToolbar


class ProductDetails : AppCompatActivity() {
    companion object {
        const val EXTRA_POST = "extra_post"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_details)


        val backbutton : MaterialToolbar = findViewById(R.id.backbutton)
        backbutton.setOnClickListener {
            super.onBackPressed()
        }
        val post = intent.getParcelableExtra<PostEntity>(EXTRA_POST)
        val imageView = findViewById<ImageView>(R.id.imagedetails)
        if (post != null) {
            findViewById<TextView>(R.id.Iddetails).text = "${post.id} :"
            findViewById<TextView>(R.id.titledetails).text = post.title
            findViewById<TextView>(R.id.pricedetails).text = "Price : ${post.price}₹"
            findViewById<TextView>(R.id.descriptiondetails).text = "Description : ${post.description}"
            findViewById<TextView>(R.id.categorydetails).text = "Category : ${post.category}"
            findViewById<TextView>(R.id.ratingdetails).text = "${post.rating}"

            Glide.with(this)
                .load(post.image)
                .into(imageView)

        }
    }
}