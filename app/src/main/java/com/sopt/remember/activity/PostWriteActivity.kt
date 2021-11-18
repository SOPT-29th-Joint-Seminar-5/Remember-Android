package com.sopt.remember.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.sopt.remember.R
import com.sopt.remember.databinding.ActivityPostWriteBinding
import com.sopt.remember.fragment.CategoryDialog

class PostWriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPostWriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostWriteBinding.inflate(layoutInflater)

        checkEtTitle()
        checkEtContent()
        clickBtnCategory()
        clickBtnPosting()
        clickBtnCancel()

        setContentView(binding.root)
    }

    private fun checkEtTitle() {
        binding.etTitle.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (binding.etTitle.text.isNotEmpty() && binding.etContent.text.isNotEmpty()) {
                    binding.tvPosting.setTextColor(getColor(R.color.black))
                } else {
                    binding.tvPosting.setTextColor(getColor(R.color.gray2))
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    private fun checkEtContent()  {
        binding.etContent.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (binding.etTitle.text.isNotEmpty() && binding.etContent.text.isNotEmpty()) {
                    binding.tvPosting.setTextColor(getColor(R.color.black))
                } else {
                    binding.tvPosting.setTextColor(getColor(R.color.gray2))
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    private fun clickBtnCancel() {
        binding.btCancel.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_down)
        }
    }

    private fun clickBtnPosting() {
        binding.clPosting.setOnClickListener {
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