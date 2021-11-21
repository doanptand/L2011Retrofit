package com.ddona.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ddona.retrofit.adapter.CommentAdapter
import com.ddona.retrofit.databinding.ActivityMainBinding
import com.ddona.retrofit.model.Comment
import com.ddona.retrofit.network.CommentClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val comments = arrayListOf<Comment>()
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CommentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = CommentAdapter(comments)
        binding.rvComments.adapter = adapter
        binding.rvComments.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        getAllCommentWithCoroutines()
//        getAllCommentAsync()
//        getAllCommentSync()
        //..code
    }

    private fun getAllCommentWithCoroutines() {
        lifecycleScope.launch(Dispatchers.IO) {
            val data = CommentClient().getAllCommentsWithCoroutines()
            comments.addAll(data)
            withContext(Dispatchers.Main) {
                adapter.notifyDataSetChanged()
            }
        }
    }

    private fun getAllCommentAsync() {
        CommentClient().getAllComments().enqueue(object : Callback<List<Comment>> {
            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                if (response.isSuccessful) {
                    val data = response.body()
                    data?.let {
                        comments.addAll(data)
                        adapter.notifyDataSetChanged()
                    }
                }
            }

            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                Log.d("doanpt", "${t.message}")
                t.printStackTrace()
            }

        })
        Log.d("doanpt", "get size:${comments.size}")
        adapter.notifyDataSetChanged()
    }

    private fun getAllCommentSync() {
        Log.d("doanpt", "before calll")
        lifecycleScope.launch(Dispatchers.IO) {
            val response: Response<List<Comment>> = CommentClient().getAllComments().execute()
            if (response.isSuccessful) {
                val data = response.body()
                data?.let {
                    comments.addAll(it)
                    Log.d("doanpt", "All comment size:${comments.size}")
                    withContext(Dispatchers.Main) {
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        }
        Log.d("doanpt", "After calll")
    }
}