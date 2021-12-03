package com.sopt.remember.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.Toast
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
    private var category: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostWriteBinding.inflate(layoutInflater)

        clickBtnCategory()
        checkEnterStatus()
        clickBtnPosting()
        clickBtnCancel()

        setContentView(binding.root)
    }

    private fun checkEnterStatus(){
        checkEmptyText(binding.etTitle)
        checkEmptyText(binding.etContent)
    }

    private fun checkEmptyText(et: EditText) {
        et.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                changePostingBtnColor()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    private fun changePostingBtnColor() {
        if (enteredAll()) {
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
            if (enteredAll()) { initNetwork() }
        }
    }

    private fun enteredAll(): Boolean {
        return binding.etTitle.text.isNotEmpty() && binding.etContent.text.isNotEmpty() && category != null
    }

    private fun initNetwork() {
        val requestPostWriteData = RequestPostWriteData(
            tagName = binding.tvSelectCategory.text.toString(),
            subject = binding.etTitle.text.toString(),
            contents = binding.etContent.text.toString()
        )

        val call: Call<ResponsePostWriteData> =
            ServiceCreator.postService.postWrite(requestPostWriteData)

        call.enqueue(object : Callback<ResponsePostWriteData> {
            override fun onResponse(
                call: Call<ResponsePostWriteData>,
                response: Response<ResponsePostWriteData>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()?.data
                    startPostViewActivity(data!!.post.id)
                } else {
                    Toast.makeText(this@PostWriteActivity, "response error", Toast.LENGTH_SHORT).show()
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
            val bottomSheet = CategoryDialogFragment {
                category = it
                category?.let {
                    binding.tvSelectCategory.setText(category!!)
                }
                changePostingBtnColor()
                // 세부 카테고리를 선택했을때 작동
            }
            bottomSheet.show(supportFragmentManager, bottomSheet.tag)
        }
    }
}