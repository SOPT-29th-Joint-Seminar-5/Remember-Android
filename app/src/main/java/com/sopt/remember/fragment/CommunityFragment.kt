package com.sopt.remember.fragment

import android.graphics.drawable.ClipDrawable.HORIZONTAL
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sopt.remember.databinding.FragmentCommunityBinding
import com.sopt.remember.adapter.BestPostAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.sopt.remember.util.BestPostData

class CommunityFragment : Fragment() {
    private var _binding: FragmentCommunityBinding? = null
    private val binding get() = _binding!!

    private lateinit var bestPostAdapter : BestPostAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCommunityBinding.inflate(layoutInflater, container, false)

        initAdapter()
        addDivider()

        return binding.root
    }

    private fun addDivider() {
        binding.rvBestPost.addItemDecoration(
            DividerItemDecoration(binding.rvBestPost.context, HORIZONTAL)
        )
    }


    private fun initAdapter() {

        Log.d("CommunityFragment", "initAdapter")

        bestPostAdapter = BestPostAdapter()
        binding.rvBestPost.adapter = bestPostAdapter

        bestPostAdapter.bestPostList.addAll(
            listOf(
                BestPostData(1,"우리 모두","카테1",243, 53),
                BestPostData(2,"고생 많았다~~!!","카테2",243, 53),
                BestPostData(3,"남은 서버도","카테3",243, 53),
                BestPostData(4,"홧팅!!!!","카테4",243, 53),
                BestPostData(5,"내일 할일","카테4",243, 53),
                BestPostData(6,"아이템 데코레이션-회색 라인","카테4",243, 53),
                BestPostData(7,"번호 123은 주황색, 나머지는 회색...","카테4",243, 53),
                BestPostData(8,"번호만 따로 관리하는법 없나...?","도와주면 착한사람..",243, 53),
            )
        )

        bestPostAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}