package com.example.myapplication.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.myapplication.R
import com.example.myapplication.base.BaseActivity
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.ui.main.fragments.HomeFragment
import com.example.myapplication.ui.main.fragments.SettingFragment
import com.example.myapplication.ui.main.newVisitor.NewVisitorCameraActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(), View.OnClickListener {


    lateinit var binding: ActivityMainBinding
    lateinit var currentTextView: TextView
    lateinit var currentImageView: ImageView
    lateinit var currentNavigation: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = viewDataBinding!!

        currentTextView = binding.homeText
        currentImageView = binding.homeIcon
        currentNavigation = "homeNavigation"
        replaceFragment(HomeFragment(), "HomeFragment")

        setListeners()
    }

    private fun setListeners() {
        binding.home.setOnClickListener(this)
        binding.setting.setOnClickListener(this)
        binding.addNew.setOnClickListener(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }


    override fun onClick(v: View?) {
        when (v?.id) {

            R.id.home -> {

                if (currentNavigation != "homeNavigation") {

                    clearCurrentViewsFromColor()
                    currentTextView = binding.homeText
                    currentImageView = binding.homeIcon
                    currentNavigation = "homeNavigation"

                    binding.homeIcon.setColorFilter(
                        ContextCompat.getColor(this, R.color.primary),
                        android.graphics.PorterDuff.Mode.SRC_IN
                    );
                    binding.homeText.setTextColor(ContextCompat.getColor(this, R.color.primary));

                    replaceFragment(HomeFragment(), "HomeFragment")
                }

            }
            R.id.setting -> {

                if (currentNavigation != "setting") {

                    clearCurrentViewsFromColor()
                    currentTextView = binding.settingText
                    currentImageView = binding.settingIcon
                    currentNavigation = "setting"

                    binding.settingIcon.setColorFilter(
                        ContextCompat.getColor(
                            this,
                            R.color.primary
                        ), android.graphics.PorterDuff.Mode.SRC_IN
                    );
                    binding.settingText.setTextColor(
                        ContextCompat.getColor(
                            this,
                            R.color.primary
                        )
                    )

                    replaceFragment(SettingFragment(), "setting")
                }
            }

            R.id.addNew -> {

                startActivity(Intent(this , NewVisitorCameraActivity::class.java))
            }
        }
    }


    private fun replaceFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in_left,
                R.anim.slide_out_left,
                R.anim.slide_in_left,
                R.anim.slide_out_left
            )
            .add(
                R.id.container,
                fragment
            )
            .addToBackStack(tag)
            .commit()
    }


    private fun clearCurrentViewsFromColor() {
        currentImageView.setColorFilter(
            ContextCompat.getColor(this, R.color.grey),
            android.graphics.PorterDuff.Mode.SRC_IN
        );
        currentTextView.setTextColor(ContextCompat.getColor(this, R.color.grey));


    }
}