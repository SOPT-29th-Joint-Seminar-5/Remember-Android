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
        clickBtnBack()
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
            it.getIntExtra("category", R.string.rb_job_designer).let { sub ->
                binding.tvSubCategory.text = getString(sub)
                binding.tvMainCategory.text = when (sub) {
                    R.id.rb_job_designer, R.id.rb_job_marketing, R.id.rb_job_IT, R.id.rb_job_growup
                        -> getString(R.string.tv_job_talk)
                    else -> getString(R.string.tv_interest_talk)
                }
            }
        }
    }

    private fun clickBtnBack() {
        binding.ibBack.setOnClickListener {
            finish()
        }
    }
}