package com.tms.android.sxtask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.fragment.NavHostFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.frame1)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.listFragment2) as NavHostFragment?
        val navController = navHostFragment?.navController

        if (currentFragment == null){
            val fragment = ListFragment.newInstance()
            supportFragmentManager.beginTransaction().add(R.id.frame1, fragment).commit()
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if(item.itemId == android.R.id.home){
//            SecondScreenActivity.
//        }
//
//        return true
//    }
}