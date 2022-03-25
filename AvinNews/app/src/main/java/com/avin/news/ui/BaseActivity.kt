package com.avin.news.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.avin.news.R
import com.avin.news.databinding.ActivityBaseBinding

abstract class BaseActivity <T : ViewDataBinding> : AppCompatActivity() {
    lateinit var baseBinding: ActivityBaseBinding;
    lateinit var binding: T


    @get:LayoutRes
    protected abstract val layoutResourceId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseBinding = DataBindingUtil.setContentView(this, R.layout.activity_base)

        setSupportActionBar(baseBinding.toolbar)
        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = DataBindingUtil.inflate(inflater, layoutResourceId, baseBinding.layoutContainer, true)
        baseBinding.ivBack.setOnClickListener({ onBackPressed() })
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId()
        if (id == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }


}