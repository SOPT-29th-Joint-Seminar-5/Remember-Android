package com.sopt.remember.fragment

import android.content.Context
import android.content.Intent
import android.graphics.drawable.ClipDrawable.HORIZONTAL
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.sopt.remember.databinding.FragmentCommunityBinding
import com.sopt.remember.adapter.BestPostAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.bumptech.glide.Glide
import com.sopt.remember.activity.PostViewActivity
import com.sopt.remember.activity.ServiceCreator
import com.sopt.remember.util.BestPostData
import com.sopt.remember.util.ResponseMainViewData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommunityFragment : Fragment() {
    private var _binding: FragmentCommunityBinding? = null
    private val binding get() = _binding!!
    private lateinit var bestPostAdapter: BestPostAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCommunityBinding.inflate(layoutInflater, container, false)

        addDivider()        // 구분선

        return binding.root
    }

    override fun onStart() {
        // 다른 View 로 갔다가 돌아올때 서버로부터 최신상태 데이터 불러오기 위함
        super.onStart()
        initNetwork()
    }

    private fun initNetwork() {
        val call: Call<ResponseMainViewData> = ServiceCreator.postService.getData()

        call.enqueue(object : Callback<ResponseMainViewData> {
            override fun onResponse(
                call: Call<ResponseMainViewData>,
                response: Response<ResponseMainViewData>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()?.data
                    initAdapter(data?.mainList)
                    initImage(data?.image.toString())
                } else
                    Toast.makeText(requireActivity(), "response error", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<ResponseMainViewData>, t: Throwable) {
                Log.e("CommunityFragmentNetwork", "error:$t")
            }
        })

    }

    private fun initAdapter(data: List<ResponseMainViewData.Data.MainList>?) {
        bestPostAdapter = BestPostAdapter()
        binding.rvBestPost.adapter = bestPostAdapter

        if (data != null) {
            bestPostAdapter.bestPostList.addAll(
                data.map { BestPostData(it.subject, it.tagName, it.likeCnt, it.commentCnt) }
            )
        }
        bestPostAdapter.notifyDataSetChanged()

        bestPostAdapter.setItemClickListener(object : BestPostAdapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {
                startPostViewActivity(data!![position].id.toInt())
            }
        })
    }

    private fun startPostViewActivity(id: Int) {
        val intent = Intent(context, PostViewActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }

    /* 구분선 추가 */
    private fun addDivider() {
        binding.rvBestPost.addItemDecoration(
            DividerItemDecoration(binding.rvBestPost.context, HORIZONTAL)
        )
    }

    private fun initImage(data: String) {
        Glide.with(this)
            .load(data)
            .into(binding.banner1)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}