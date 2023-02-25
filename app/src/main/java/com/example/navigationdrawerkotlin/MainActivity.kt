package com.example.navigationdrawerkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.navigationdrawerkotlin.databinding.ActivityMainBinding
import com.example.navigationdrawerkotlin.fragments.AboutFragment
import com.example.navigationdrawerkotlin.fragments.HomeFragment
import com.example.navigationdrawerkotlin.fragments.SettingsFragment
import com.example.navigationdrawerkotlin.fragments.ShareFragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    //Application binding
    private var _binding: ActivityMainBinding? = null

    //Some other binding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toolbar: Toolbar
    private lateinit var navigationView: NavigationView
    private lateinit var drawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding!!.root)

        //DrawerLayout
        drawerLayout = findViewById(R.id.drawer_layout)

        //Toolbar
        toolbar = findViewById(R.id.drawer_toolbar)
        setSupportActionBar(toolbar)

        //NavigationView
        navigationView = findViewById(R.id.navigation_view)
        navigationView.setNavigationItemSelectedListener(this)

        //ActionToggle
        drawerToggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav)

        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, HomeFragment()).commit()

            navigationView.setCheckedItem(R.id.navigation_home)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.navigation_home -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, HomeFragment()).commit()

            R.id.navigation_settings -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, SettingsFragment()).commit()

            R.id.navigation_share -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, ShareFragment()).commit()

            R.id.navigation_info -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, AboutFragment()).commit()

            R.id.navigation_logout -> Toast.makeText(this, "Logout", Toast.LENGTH_LONG).show()
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START)
        }else{
            onBackPressedDispatcher.onBackPressed()
        }
    }

}