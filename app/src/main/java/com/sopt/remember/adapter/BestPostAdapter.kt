package com.sopt.remember.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.sopt.remember.R
import com.sopt.remember.databinding.ItemBestPostBinding
import com.sopt.remember.util.BestPostData

class BestPostAdapter : RecyclerView.Adapter<BestPostAdapter.BestPostViewHolder>() {
    val bestPostList = mutableListOf<BestPostData>()

    class BestPostViewHolder(private val binding: ItemBestPostBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: BestPostData, position: Int) {
            binding.tvPostNum.text = (position + 1).toString()
            binding.tvTitle.text = data.title
            binding.tvCategory.text = data.category
            binding.tvLikeNum.text = data.like_num.toString()
            binding.tvCommentNum.text = data.comment_num.toString()

            if(position < 3) {
                binding.tvPostNum.setTextColor(
                    ContextCompat.getColor(itemView.context, R.color.main1)
                )
            }
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
        holder.onBind(bestPostList[position], position)
    }

    override fun getItemCount(): Int = bestPostList.size
}