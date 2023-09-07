package com.adityastudios.interiitappdev

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.adityastudios.interiitappdev.databinding.ActivityMainBinding
import com.adityastudios.interiitappdev.fragments.FitnessFragment
import com.adityastudios.interiitappdev.fragments.ProfileFragment
import com.adityastudios.interiitappdev.fragments.RecipeFragment
import com.adityastudios.interiitappdev.fragments.SettingsFragment
import com.adityastudios.interiitappdev.fragments.StudyFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //setting initial fragment as profile
        binding.btmNavBar.selectedItemId =R.id.Profile
        replaceFragment(ProfileFragment())


        //replacing fragments with selected bottom nav
        binding.btmNavBar.setOnItemSelectedListener {
            when(it.itemId){
                R.id.Profile -> replaceFragment(ProfileFragment())
                R.id.Settings -> replaceFragment(SettingsFragment())
                R.id.Fitness -> replaceFragment(FitnessFragment())
                R.id.Recipe -> replaceFragment(RecipeFragment())
                R.id.Study -> replaceFragment(StudyFragment())
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
    }
}