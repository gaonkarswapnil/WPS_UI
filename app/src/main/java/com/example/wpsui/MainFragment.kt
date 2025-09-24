package com.example.wpsui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.commit
import com.example.wpsui.databinding.FragmentMainBinding
import com.google.android.material.tabs.TabLayout

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupTabs()
        loadFragment("Available") // default tab
    }


    private fun setupTabs() {
        // Clear existing tabs
        binding.tabLayout.removeAllTabs()

        // Tab names
//        val tabNames = listOf("Available", "History")
//        for (name in tabNames) {
//            val tab = binding.tabLayout.newTab()
//            tab.setCustomView(R.layout.custom_tab)
//            val textView = tab.customView?.findViewById<TextView>(R.id.tabText)
//            textView?.text = name
//            tab.customView?.isSelected = tab.position == 0 // select first tab by default
//            binding.tabLayout.addTab(tab)
//        }
        val tabNames = listOf("Available", "History")
        for (name in tabNames) {
            binding.tabLayout.addTab(binding.tabLayout.newTab().setText(name))
        }

        // Tab selection listener
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                tab.customView?.isSelected = true
                val type = if (tab.position == 0) "Available" else "History"
                loadFragment(type)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                tab.customView?.isSelected = false
            }

            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    private fun loadFragment(tabType: String) {
        requireActivity().supportFragmentManager.commit {
            replace(R.id.fragmentContainer, CommonFragment.newInstance(tabType))
        }
    }
}