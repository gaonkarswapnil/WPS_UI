package com.example.wpsui.salary

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wpsui.R
import com.example.wpsui.databinding.ActivitySalariesBinding
import com.example.wpsui.salary.BankAccount
import com.example.wpsui.salary.adapter.BankAccountAdapter
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SalariesActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySalariesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySalariesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.layoutSalariesShimmerList.root.visibility = View.VISIBLE
        binding.layoutSalariesShimmerList.shimmerFrameLayout.startShimmer()
        binding.layoutSalariesList.root.visibility = View.GONE


        val sampleBankAccounts = listOf(
            BankAccount(
                number = 1,
                accountHolderName = "Abdulrahman Khaled Youssef Al-Salem Al-Harbi",
                employerName = "National Bank of Bahrain",
                accountNumber = "BH43 5000 0012 3456 7890 1234",
                gcc = "482936157",
                amount = "BHD 12,121.002"
            ),
            BankAccount(
                number = 2,
                accountHolderName = "Mohammed Ahmed Hassan Al-Khalifa",
                employerName = "Gulf International Bank",
                accountNumber = "BH67 8900 1234 5678 9012 3456",
                gcc = "593847261",
                amount = "BHD 8,450.500"
            ),
            BankAccount(
                number = 3,
                accountHolderName = "Fatima Zahra Ibrahim Al-Mannai",
                employerName = "Ahli United Bank",
                accountNumber = "BH29 3456 7890 1234 5678 9012",
                gcc = "721594836",
                amount = "BHD 25,789.750"
            ),
            BankAccount(
                number = 4,
                accountHolderName = "Ali Hassan Mohammed Al-Dosari",
                employerName = "BBK Bank",
                accountNumber = "BH84 1122 3344 5566 7788 9900",
                gcc = "864259173",
                amount = "BHD 3,210.125"
            ),
            BankAccount(
                number = 5,
                accountHolderName = "Sarah Abdullah Khalid Al-Rumaihi",
                employerName = "Ithmaar Bank",
                accountNumber = "BH51 9988 7766 5544 3322 1100",
                gcc = "419263857",
                amount = "BHD 16,543.890"
            ),
            BankAccount(
                number = 6,
                accountHolderName = "Khalid Hamad Salman Al-Fadhel",
                employerName = "Kuwait Finance House",
                accountNumber = "BH73 2468 1357 9024 6813 5790",
                gcc = "537281946",
                amount = "BHD 45,092.300"
            )
        )


        lifecycleScope.launch {
            delay(5000)
            with(binding){
                layoutSalariesShimmerList.root.visibility = View.GONE
                layoutSalariesShimmerList.shimmerFrameLayout.stopShimmer()
                layoutSalariesList.root.visibility = View.VISIBLE

                layoutSalariesList.recyclerViewSalaries.layoutManager = LinearLayoutManager(this@SalariesActivity)
                layoutSalariesList.recyclerViewSalaries.adapter = BankAccountAdapter(sampleBankAccounts)
            }
        }




    }
}