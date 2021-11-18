package com.sopt.remember.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sopt.remember.databinding.ItemBestPostBinding

class BestPostAdapter : RecyclerView.Adapter<BestPostAdapter.BestPostViewHolder>() {
    val bestPostList = mutableListOf<BestPostData>()

    class BestPostViewHolder(private val binding: ItemBestPostBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: BestPostData) {
            binding.tvTitle.text = data.title
            binding.tvCategory.text = data.category
            binding.tvLikeNum.text = data.like_num.toString()
            binding.tvCommentNum.text = data.comment_num.toString()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BestPostViewHolder {
        val binding = ItemBestPostBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )

        return BestPostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BestPostViewHolder, position: Int) {
        holder.onBind(bestPostList[position])
    }

    override fun getItemCount(): Int = bestPostList.size
}