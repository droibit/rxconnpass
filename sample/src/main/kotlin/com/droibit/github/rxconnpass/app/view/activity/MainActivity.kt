package com.droibit.github.rxconnpass.app.view.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.droibit.github.rxconnpass.app.R

class MainActivity: AppCompatActivity() {

    //private lateinit var mBinding: ActivityMainBinding

    /** {@inheritDoc}  */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        //mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }
}
