package com.sopt.remember.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import com.sopt.remember.R
import com.sopt.remember.databinding.ActivityPostWriteBinding
import com.sopt.remember.fragment.CategoryDialogFragment
import com.sopt.remember.util.RequestPostWriteData
import com.sopt.remember.util.ResponsePostWriteData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostWriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPostWriteBinding
    var category: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostWriteBinding.inflate(layoutInflater)

        clickBtnCategory()
        checkEmptyText(binding.etTitle)
        checkEmptyText(binding.etContent)
        clickBtnPosting()
        clickBtnCancel()
        setContentView(binding.root)
    }

    private fun checkEmptyText(et: EditText) {
        et.addTextChangedListener(object : TextWatcher {
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
//                startPostViewActivity(title.toString(), content.toString())
//                finish()
                initNetwork()
            }
        }
    }

    private fun initNetwork() {
        val requestPostWriteData = RequestPostWriteData(
            tagName = binding.tvSelectCategory.text.toString(),
            subject = binding.etTitle.text.toString(),
            contents = binding.etContent.text.toString()
        )

        val call: Call<ResponsePostWriteData> =
            PostServiceCreator.postService.postWrite(requestPostWriteData)

        call.enqueue(object: Callback<ResponsePostWriteData> {
            override fun onResponse(
                call: Call<ResponsePostWriteData>,
                response: Response<ResponsePostWriteData>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()?.data
                    Log.d("postwrite_content", data!!.post.contents)
                    startPostViewActivity(data!!.post.id)
//                    startActivity(Intent(this@PostWriteActivity, PostViewActivity::class.java))
//                    finish()
                }
            }

            override fun onFailure(call: Call<ResponsePostWriteData>, t: Throwable) {
                Log.e("PostWriteActivityNetwork", "error:$t")
            }
        })
    }

    private fun startPostViewActivity(id: Int) {
        val intent = Intent(this@PostWriteActivity, PostViewActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
        finish()
    }

    private fun clickBtnCategory() {
        binding.clSelectCategory.setOnClickListener {
            // 상단 회색 부분을 클릭했을때 작동
            // Log.d("ClickBtnCategory", "CheckTiming")
            val bottomSheet = CategoryDialogFragment {
                category = it
                category?.let {
                    binding.tvSelectCategory.setText(category!!)
                }
                changePostingColor()
                // 세부 카테고리를 선택했을때 작동
                //                // Log.d("ClickBtnCategory", "CheckRadioButton?")
            }
            bottomSheet.show(supportFragmentManager, bottomSheet.tag)
        }
    }
}