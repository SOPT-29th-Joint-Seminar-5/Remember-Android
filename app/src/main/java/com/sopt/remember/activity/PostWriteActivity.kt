package com.sopt.remember.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.sopt.remember.R
import com.sopt.remember.databinding.ActivityPostWriteBinding
import com.sopt.remember.fragment.CategoryDialog

class PostWriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPostWriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostWriteBinding.inflate(layoutInflater)

        clickBtnCategory()
        setContentView(binding.root)
    }

    private fun clickBtnCategory() {
        binding.clSelectCategory.setOnClickListener {
            val bottomSheet = CategoryDialog()
//            val bottomSheetBehavior = BottomSheetBehavior.from(binding.clSelectCategory)
//            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            bottomSheet.show(supportFragmentManager, bottomSheet.tag)
        }
    }
}