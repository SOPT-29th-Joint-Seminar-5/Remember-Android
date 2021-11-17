package com.sopt.remember.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.sopt.remember.R
import com.sopt.remember.databinding.ActivityPostWriteBinding
import com.sopt.remember.fragment.CategoryDialog

class PostWriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPostWriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostWriteBinding.inflate(layoutInflater)

        clickBtnCategory()
        clickBtnPosting()
        setContentView(binding.root)
    }

    private fun clickBtnPosting() {
        binding.btPosting.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val content = binding.etContent.text.toString()
            if (title.isNotEmpty() && content.isNotEmpty()) {
                startPostViewActivity(title, content)
            }
        }
    }

    private fun startPostViewActivity(title: String, content: String) {
        val intent = Intent(this@PostWriteActivity, PostViewActivity::class.java)
        intent.putExtra("title", title)
        intent.putExtra("content", content)
        startActivity(intent)
    }

    private fun clickBtnCategory() {
        binding.clSelectCategory.setOnClickListener {
            val bottomSheet = CategoryDialog()
            bottomSheet.show(supportFragmentManager, bottomSheet.tag)
        }
    }
}