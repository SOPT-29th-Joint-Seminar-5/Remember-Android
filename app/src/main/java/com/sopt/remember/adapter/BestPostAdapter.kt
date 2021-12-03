package com.sopt.remember.adapter

import android.view.LayoutInflater
import android.view.View
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

            if (position < 3)
                binding.tvPostNum.setTextColor(
                    ContextCompat.getColor(itemView.context, R.color.main1)
                )
            else
                binding.tvPostNum.setTextColor(
                    ContextCompat.getColor(itemView.context, R.color.gray3)
                )
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
        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
    }

    // 리스너 인터페이스
    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }

    // 외부에서 클릭 시 이벤트 설정
    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }

    // setItemClickListener로 설정한 함수 실행
    private lateinit var itemClickListener : OnItemClickListener

    override fun getItemCount(): Int = bestPostList.size
}