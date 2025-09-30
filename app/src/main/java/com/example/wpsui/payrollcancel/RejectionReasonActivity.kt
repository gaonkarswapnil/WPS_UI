package com.example.wpsui.payrollcancel

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wpsui.payrollcancel.adapter.RejectionAdapter
import com.example.wpsui.databinding.ActivityRejectionReasonBinding

class RejectionReasonActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRejectionReasonBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRejectionReasonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val list = listOf<String>(
            "Wrong debit account",
            "Wrong total debit account",
            "Wrong credit account registered",
            "Wrong one or more credit account",
            "Wrong value data",
            "Missing one or more credit account",
            "Wrong employer name"
        )

        val adapter = RejectionAdapter(list) { selectedReason ->
            binding.btnContinue.isEnabled = true
        }

        binding.recyclerViewReasons.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewReasons.adapter = adapter

        binding.btnContinue.setOnClickListener {
            val selectedReason = adapter.getSelectedReason()
            Toast.makeText(this, "Selected: $selectedReason", Toast.LENGTH_SHORT).show()
        }
    }
}