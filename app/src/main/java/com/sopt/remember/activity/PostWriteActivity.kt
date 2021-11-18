package com.sopt.remember.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.sopt.remember.R
import com.sopt.remember.databinding.ActivityPostWriteBinding
import com.sopt.remember.fragment.CategoryDialogFragment

class PostWriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPostWriteBinding
    var category: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostWriteBinding.inflate(layoutInflater)

        clickBtnCategory()
        checkEtTitle()
        checkEtContent()
        clickBtnPosting()
        clickBtnCancel()
        setContentView(binding.root)
    }

    private fun checkEtTitle() {
        binding.etTitle.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                changePostingColor()
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
                changePostingColor()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    private fun changePostingColor() {
        if (binding.etTitle.text.isNotEmpty() && binding.etContent.text.isNotEmpty() && category != null) {
            binding.tvPosting.setTextColor(getColor(R.color.black))
        } else {
            binding.tvPosting.setTextColor(getColor(R.color.gray2))
        }
    }

    private fun clickBtnCancel() {
        binding.btCancel.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_down)
        }
    }

    private fun clickBtnPosting() {
        binding.clPosting.setOnClickListener {
            val title = binding.etTitle.text
            val content = binding.etContent.text
            if (title.isNotEmpty() && content.isNotEmpty() && category != null) {
                startPostViewActivity(title.toString(), content.toString())
            }
        }
    }

    private fun startPostViewActivity(title: String, content: String) {
        val intent = Intent(this@PostWriteActivity, PostViewActivity::class.java)
        intent.putExtra("title", title)
        intent.putExtra("content", content)
        intent.putExtra("category", category)
        startActivity(intent)
    }

    private fun clickBtnCategory() {
        binding.clSelectCategory.setOnClickListener {
            val bottomSheet = CategoryDialogFragment {
                category = it
                category?.let {
                    binding.tvSelectCategory.setText(category!!)
                }
                changePostingColor()
            }
            bottomSheet.show(supportFragmentManager, bottomSheet.tag)
        }
    }
}