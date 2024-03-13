package com.example.genericrecyclerlist

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.genericrecyclerlist.databinding.ActivityMainBinding
import com.example.genericrecyclerlist.ui.Utils
import com.example.genericrecyclerlist.ui.dashboard.DashboardFragment
import com.example.genericrecyclerlist.ui.home.HomeFragment
import com.marketo.Marketo

class MainActivity : AppCompatActivity() {

private lateinit var binding: ActivityMainBinding
private var fragmentManager: FragmentManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

     binding = ActivityMainBinding.inflate(layoutInflater)
        val marketoSdk = Marketo.getInstance(applicationContext)
        marketoSdk.initializeSDK("native", "310-HII-890", "")
     setContentView(binding.root)

        Utils.startTimer()
        fragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager!!.beginTransaction()
        fragmentTransaction.apply {
            add(binding.container.id, HomeFragment())
        }
        fragmentTransaction.commitAllowingStateLoss()
        fragmentManager!!.executePendingTransactions()

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
    }
}