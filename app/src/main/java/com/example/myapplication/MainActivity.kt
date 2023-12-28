package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PostAdapter
    private lateinit var progressBar : ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        recyclerView = findViewById(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)


        progressBar = findViewById(R.id.progressBar)
        progressBar.visibility = View.VISIBLE


        adapter = PostAdapter(this, object : PostAdapter.OnItemClickListener {
            override fun onImageClick(post: PostEntity) {
                val intent = Intent(this@MainActivity, ProductDetails::class.java)
                intent.putExtra(ProductDetails.EXTRA_POST, post)
                startActivity(intent)
            }
        })

        recyclerView.adapter = adapter

        if (networkAvailable()){
            fetchdata()
        }else{
            Toast.makeText(this,"No internet connection",Toast.LENGTH_SHORT).show()
        }



    }

    private fun fetchdata() {
        val progressBar : ProgressBar = findViewById(R.id.progressBar)
        progressBar.visibility = View.VISIBLE
        call.enqueue(object : Callback<List<PostEntity>> {
            override fun onResponse(call: Call<List<PostEntity>>, response: Response<List<PostEntity>>) {
                progressBar.visibility = View.GONE
                if (response.isSuccessful) {
                    val posts = response.body()
                    posts?.let {
                        adapter.setPosts(it)
                    }
                }
            }

            override fun onFailure(call: Call<List<PostEntity>>, t: Throwable) {
                progressBar.visibility = View.GONE
            }
        })
    }

    private fun networkAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
        val activeNetworkInfo = connectivityManager?.activeNetworkInfo
        return activeNetworkInfo?.isConnectedOrConnecting == true

    }
}


