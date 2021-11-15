package com.sopt.remember.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sopt.remember.R
import com.sopt.remember.databinding.ActivityHomeBinding
import com.sopt.remember.fragment.CommunityFragment

class HomeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val fragment1 = CommunityFragment()

        supportFragmentManager.beginTransaction().add(R.id.container, fragment1).commit()



    }
}