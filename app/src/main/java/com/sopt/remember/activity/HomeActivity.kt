package com.sopt.remember.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.sopt.remember.R
import com.sopt.remember.adapter.HomeViewPagerAdapter
import com.sopt.remember.databinding.ActivityHomeBinding
import com.sopt.remember.fragment.BusinessCardFragment
import com.sopt.remember.fragment.CareerFragment
import com.sopt.remember.fragment.CommunityFragment
import com.sopt.remember.fragment.MypageFragment


class HomeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHomeBinding
    private lateinit var homeViewPagerAdapter: HomeViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)

        binding.fabPost.bringToFront()
        binding.fabPost.setOnClickListener {
            val intent = Intent(this@HomeActivity, PostWriteActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up)
        }

        initAdapter()
        initBottomNavigation()

        setContentView(binding.root)

    }

    private fun initAdapter() {
        val fragmentList = listOf(BusinessCardFragment(), CareerFragment(), CommunityFragment(), MypageFragment())

        homeViewPagerAdapter = HomeViewPagerAdapter(this)
        homeViewPagerAdapter.fragments.addAll(fragmentList)

        binding.vpHome.adapter = homeViewPagerAdapter
    }

    private fun initBottomNavigation() {
        binding.vpHome.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.bnv.menu.getItem(position).isChecked = true
            }
        })

        binding.bnv.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.menu_bussinesscard -> {
                    binding.vpHome.currentItem = FIRST_FRAGMENT
                    return@setOnItemSelectedListener true
                }
                R.id.menu_career -> {
                    binding.vpHome.currentItem = SECOND_FRAGMENT
                    return@setOnItemSelectedListener true
                }
                R.id.menu_empty -> {
                    return@setOnItemSelectedListener false
                }
                R.id.menu_community -> {
                    binding.vpHome.currentItem = THIRD_FRAGMENT
                    return@setOnItemSelectedListener true
                }
                else -> {
                    binding.vpHome.currentItem = FORTH_FRAGMENT
                    return@setOnItemSelectedListener true
                }
            }
        }

        // 스와이프 안되게
        binding.vpHome.isUserInputEnabled = false
    }

    companion object {
        const val FIRST_FRAGMENT = 0
        const val SECOND_FRAGMENT = 1
        const val THIRD_FRAGMENT = 2
        const val FORTH_FRAGMENT = 3
    }
}