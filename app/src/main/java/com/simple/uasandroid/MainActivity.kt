package com.simple.uasandroid

import android.os.Bundle
import android.widget.FrameLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.simple.uasandroid.ui.event.EventFragment

class MainActivity : AppCompatActivity() {
    private var content: FrameLayout? = null
    lateinit var bottomNav: BottomNavigationView

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_previous -> {
                    val fragment = EventFragment.newInstance("eventspastleague")
                    addFragment(fragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_next -> {
                    val fragment = EventFragment.newInstance("eventsnextleague")
                    addFragment(fragment)
                    return@OnNavigationItemSelectedListener true
                }
//                R.id.navigation_favorite -> {
 //                   val fragment = FavoriteFragment()
 //                   addFragment(fragment)
  //                  return@OnNavigationItemSelectedListener true
 //               }
            }
            false
        }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.anim.design_bottom_sheet_slide_in,
                R.anim.design_bottom_sheet_slide_out
            )
            .replace(R.id.content, fragment, fragment.javaClass.getSimpleName())
            .commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNav = findViewById(R.id.navigation)
        bottomNav.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
       // val fragment = EventFragmentt.newInstance("eventspastleague")
       // addFragment(fragment)

    }
}