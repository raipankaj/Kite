package com.pankaj.kite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import lib.pankaj.kfnet.utils.initKite

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val initNetwork = initKite()
    }
}
