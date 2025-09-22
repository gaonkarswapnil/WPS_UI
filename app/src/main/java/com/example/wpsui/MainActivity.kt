package com.example.wpsui

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.example.wpsui.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupTabs()
        loadFragment("Available") // default tab
    }

    private fun setupTabs() {
        // Clear existing tabs
        binding.tabLayout.removeAllTabs()

        // Tab names
        val tabNames = listOf("Available", "History")
        for (name in tabNames) {
            val tab = binding.tabLayout.newTab()
            tab.setCustomView(R.layout.custom_tab)
            val textView = tab.customView?.findViewById<TextView>(R.id.tabText)
            textView?.text = name
            tab.customView?.isSelected = tab.position == 0 // select first tab by default
            binding.tabLayout.addTab(tab)
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
        supportFragmentManager.commit {
            replace(R.id.fragmentContainer, CommonFragment.newInstance(tabType))
        }
    }
}
