package com.sopt.remember.fragment

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sopt.remember.R
import com.sopt.remember.databinding.FragmentDialogCategoryBinding
import kotlin.properties.Delegates

class CategoryDialog(val itemClick: (Int) -> Unit): BottomSheetDialogFragment() {
    private var _binding: FragmentDialogCategoryBinding? = null
    private val binding get() = _binding!!
    var category: Int? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return BottomSheetDialog(requireContext(), R.style.NewDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDialogCategoryBinding.inflate(layoutInflater, container, false)

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        return binding.root
    }

    companion object {
        fun newInstance(
            itemClick: (Int) -> Unit
        ): CategoryDialog {
            return CategoryDialog(itemClick)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.rgCategory.setOnCheckedChangeListener { group, checkedId ->
            category = selectCategory(checkedId)
            itemClick(category!!)
        }
        super.onViewCreated(view, savedInstanceState)
    }

    private fun selectCategory(checkedId: Int): Int {
        return when (checkedId) {
            R.id.rb_job_designer -> R.string.rb_job_designer
            R.id.rb_job_marketing -> R.string.rb_job_marketing
            R.id.rb_job_IT -> R.string.rb_job_IT
            R.id.rb_job_growup -> R.string.rb_job_growup
            R.id.rb_interest_company -> R.string.rb_interest_company
            R.id.rb_interest_discussion -> R.string.rb_interest_discussion
            R.id.rb_interest_economy -> R.string.rb_interest_economy
            R.id.rb_interest_hobby -> R.string.rb_interest_hobby
            R.id.rb_interest_career -> R.string.rb_interest_career
            R.id.rb_interest_retire -> R.string.rb_interest_retire
            R.id.rb_interest_global -> R.string.rb_interest_global
            R.id.rb_interest_employee1 -> R.string.rb_interest_employee1
            R.id.rb_interest_employee2 -> R.string.rb_interest_employee2
            R.id.rb_interest_employee3 -> R.string.rb_interest_employee3
            R.id.rb_interest_wine -> R.string.rb_interest_wine
            else -> R.string.rb_interest_fame
        }
    }
}