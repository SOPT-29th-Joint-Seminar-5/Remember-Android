package com.sopt.remember.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sopt.remember.R
import com.sopt.remember.databinding.ActivityPostWriteBinding

class PostWriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPostWriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostWriteBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }
}