package com.sopt.remember.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
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

        initAdapter()
        initBottomNavigation()

        binding.fabPost.bringToFront()
        binding.fabPost.setOnClickListener {
            val intent = Intent(this@HomeActivity, PostWriteActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up)
        }

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

                /** ViewPager 와 Bottom Navi 동기화 코드
                 * position = viewPager의 idx
                 * viewPager2의 2번째 페이지는 bottomNavi의 3번째 칸과 대응 (CommunityFrag)
                 * viewPager2의 3번째 페이지는 bottomNavi의 4번째 칸과 대응 (MyPageFrag)
                 * if, else if 문 구조 수정하지 마세요!
                 * */
                var btnIdx = position
                if (btnIdx== 2) btnIdx = 3
                else if (btnIdx == 3) btnIdx = 4

                binding.bnv.menu.getItem(btnIdx).isChecked = true
            }
        })

        // 커뮤니티 디폴트로 설정
        binding.vpHome.currentItem = 2
        binding.bnv.menu.getItem(3).isChecked = true

        binding.bnv.menu.getItem(2).isEnabled = false
        binding.bnv.menu.getItem(2).isCheckable = false

        //bottomNavigation 부분을 클릭했을때 작동
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
    }

    companion object {
        const val FIRST_FRAGMENT = 0
        const val SECOND_FRAGMENT = 1
        const val THIRD_FRAGMENT = 2
        const val FORTH_FRAGMENT = 3
    }
}