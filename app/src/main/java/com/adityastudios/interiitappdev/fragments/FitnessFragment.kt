package com.adityastudios.interiitappdev.fragments

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adityastudios.interiitappdev.databinding.FragmentFitnessBinding
import com.adityastudios.interiitappdev.fragments.fitnesstabs.ExerciseLogFragment
import com.adityastudios.interiitappdev.fragments.fitnesstabs.ToolsFragment
import com.adityastudios.interiitappdev.fragments.fitnesstabs.adapters.ViewPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.adityastudios.interiitappdev.R
import com.google.android.material.tabs.TabLayout


class  FitnessFragment : Fragment() {
    private lateinit var binding: FragmentFitnessBinding
    private lateinit var mContext: Context
    private lateinit var viewPager: ViewPager
    private lateinit var tabs: TabLayout


    override fun onAttach(context: Context) {
        mContext = context
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentFitnessBinding.inflate(layoutInflater,container,false)
        viewPager = binding.viewPager
        tabs = binding.tabLayout
        setUpTabs()
        return binding.root
    }

    private fun setUpTabs(){
        val adapter = ViewPagerAdapter(childFragmentManager)
        adapter.addFragment(ExerciseLogFragment(),"Exercise Logs")
        adapter.addFragment(ToolsFragment(), "Tools")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)

        tabs.getTabAt(0)!!.setIcon(R.drawable.exercise_log_image)
        tabs.getTabAt(1)!!.setIcon(R.drawable.ic_tools)

        //for making the color of icon to white
//        for (i in 0 until tabs.tabCount) {
//            val tab = tabs.getTabAt(i)
//            tab?.icon?.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN)
//        }
        val tabIconColorActive = Color.WHITE
        val tabIconColorInactive = Color.BLACK
        tabs.getTabAt(0)?.select()
        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val icon = tab.icon
                icon?.setColorFilter(tabIconColorActive, PorterDuff.Mode.SRC_IN)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                val icon = tab.icon
                icon?.setColorFilter(tabIconColorInactive, PorterDuff.Mode.SRC_IN)
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                // Do nothing or handle reselection if needed
            }
        })
    }
}

