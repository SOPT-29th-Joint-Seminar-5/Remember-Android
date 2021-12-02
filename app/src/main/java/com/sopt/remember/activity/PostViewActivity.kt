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
        val call: Call<ResponsePostViewData> = ServiceCreator.postService.getPostData(id)

        call.enqueue(object: Callback<ResponsePostViewData> {
            override fun onResponse(
                call: Call<ResponsePostViewData>,
                response: Response<ResponsePostViewData>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()?.data?.exist
                    binding.tvTitle.text = data!!.subject
                    binding.tvContent.text = data.contents
                    setNickname(data.nickname, data.duty)
                    setCategory(data.tagName)
                    setHitsCount(data.likeCnt, data.commentCnt)
                    binding.tvLikesCnt.text = data.likeCnt.toString()
                    binding.tvLikesCnt2.text = data.likeCnt.toString()
                    binding.tvCommentsCnt.text = data.commentCnt.toString()
                    binding.tvCommentsCnt2.text = data.commentCnt.toString()
                } else {
                    Toast.makeText(this@PostViewActivity, "response error", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponsePostViewData>, t: Throwable) {
                Log.e("PostViewActivityNetwork", "error:$t")
            }

        })
    }

    private fun setNickname(nickname: String?, duty: String?)
    {
        nickname?.let { binding.tvNickname.text = nickname }
        duty?.let { binding.tvDuty.text = duty }
    }

    private fun setHitsCount(likesCnt: Int, commentsCnt: Int) {
        val cnt = listOf(likesCnt, commentsCnt)
        val max = cnt.maxOrNull()
        val range = (max?.rangeTo(1000))
        binding.tvHitsCnt.text = range?.random().toString()
    }
    private fun setCategory(category: String) {
        binding.tvMainCategory.text = when (category) {
            getString(R.string.rb_job_designer), getString(R.string.rb_job_marketing), getString(R.string.rb_job_IT), getString(R.string.rb_job_growup)
                -> "직무톡"
            else -> "관심사톡"
        }
        binding.tvSubCategory.text = category
    }

    private fun clickBtnBack() {
        binding.ibBack.setOnClickListener {
            finish()
        }
    }
}