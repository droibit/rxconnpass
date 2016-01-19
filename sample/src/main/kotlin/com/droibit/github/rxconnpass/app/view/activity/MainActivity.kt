package com.droibit.github.rxconnpass.app.view.activity

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.droibit.github.rxconnpass.app.R
import com.droibit.github.rxconnpass.app.databinding.ActivityMainBinding

class MainActivity: AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    /** {@inheritDoc}  */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }
}
