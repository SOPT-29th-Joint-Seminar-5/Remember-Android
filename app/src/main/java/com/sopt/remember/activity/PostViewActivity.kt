package com.sopt.remember.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sopt.remember.R
import com.sopt.remember.databinding.ActivityPostViewBinding

class PostViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPostViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostViewBinding.inflate(layoutInflater)
        
        initContent()
        setContentView(binding.root)
    }

    private fun initContent() {
        intent?.let {
            it.getStringExtra("title")?.let { title ->
                binding.tvTitle.text = title
            }
            it.getStringExtra("content")?.let { content ->
                binding.tvContent.text = content
            }
        }
    }
}