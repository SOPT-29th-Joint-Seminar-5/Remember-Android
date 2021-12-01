package com.sopt.remember.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.sopt.remember.R
import com.sopt.remember.databinding.ActivityPostViewBinding
import com.sopt.remember.util.ResponsePostViewData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPostViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostViewBinding.inflate(layoutInflater)
        
//        initContent()
        initPostNetwork()
        clickBtnBack()
        setContentView(binding.root)
    }

    private fun initPostNetwork() {
        var id = 0
        intent?.let {
            it.getIntExtra("id", 0).let { postId ->
                id = postId
            }
        }
        val call: Call<ResponsePostViewData> = PostServiceCreator.postService.getPostData(id)

        call.enqueue(object: Callback<ResponsePostViewData> {
            override fun onResponse(
                call: Call<ResponsePostViewData>,
                response: Response<ResponsePostViewData>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()?.data?.exist
                    binding.tvTitle.text = data!!.subject
                    binding.tvNickname.text = data.nickname
                    binding.tvContent.text = data.contents
                    setCategory(data.tagName)
                    binding.tvLikesCnt.text = data.likeCnt.toString()
                    binding.tvHitsCnt.text = data.likeCnt.toString()    // hitsCnt nowhere
                    binding.tvCommentsCnt.text = data.commentCnt.toString()
                } else {
                    Toast.makeText(this@PostViewActivity, "response error", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponsePostViewData>, t: Throwable) {
                Log.e("PostViewActivityNetwork", "error:$t")
            }

        })
    }

    private fun setCategory(category: String) {
        binding.tvMainCategory.text = when (category) {
            getString(R.string.rb_job_designer), getString(R.string.rb_job_marketing), getString(R.string.rb_job_IT), getString(R.string.rb_job_growup)
                -> "직무톡"
            else -> "관심사톡"
        }
        binding.tvSubCategory.text = category
    }
//    private fun initContent() {
//        intent?.let {
//            it.getStringExtra("title")?.let { title ->
//                binding.tvTitle.text = title
//            }
//            it.getStringExtra("content")?.let { content ->
//                binding.tvContent.text = content
//            }
//            it.getIntExtra("category", R.string.rb_job_designer).let { sub ->
//                binding.tvSubCategory.text = getString(sub)
//                binding.tvMainCategory.text = when (sub) {
//                    R.id.rb_job_designer, R.id.rb_job_marketing, R.id.rb_job_IT, R.id.rb_job_growup
//                        -> getString(R.string.tv_job_talk)
//                    else -> getString(R.string.tv_interest_talk)
//                }
//            }
//        }
//    }

    private fun clickBtnBack() {
        binding.ibBack.setOnClickListener {
            finish()
        }
    }
}