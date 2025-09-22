package com.example.wpsui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wpsui.adapter.PayrollAdapter
import com.example.wpsui.databinding.FragmentAvailableBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CommonFragment : Fragment() {

    private lateinit var binding: FragmentAvailableBinding
    private var tabType: String? = null

    companion object {
        private const val ARG_TAB_TYPE = "tab_type"

        fun newInstance(tabType: String): CommonFragment {
            val fragment = CommonFragment()
            val bundle = Bundle()
            bundle.putString(ARG_TAB_TYPE, tabType)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tabType = arguments?.getString(ARG_TAB_TYPE)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAvailableBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.shimmerLayout.root.visibility = View.VISIBLE
        binding.shimmerLayout.shimmerFrameLayout.startShimmer()

        binding.availableLayout.root.visibility = View.GONE
        binding.noDataLayout.root.visibility = View.GONE

        updateButtonForTab(tabType)


        lifecycleScope.launch {
            delay(2000) // simulate loading

            val data = if (tabType == "Available") getDummyAvailable() else getDummyHistory()

            if (data.isEmpty()) {
                binding.shimmerLayout.shimmerFrameLayout.stopShimmer()
                binding.shimmerLayout.shimmerFrameLayout.visibility = View.GONE
                binding.noDataLayout.root.visibility = View.VISIBLE
                binding.availableLayout.root.visibility = View.GONE
            } else {
                showData(data)
                binding.shimmerLayout.shimmerFrameLayout.stopShimmer()
                binding.shimmerLayout.shimmerFrameLayout.visibility = View.GONE
            }
        }
    }

    private fun showData(list: List<Payroll>) {
        binding.noDataLayout.root.visibility = View.GONE
        binding.availableLayout.root.visibility = View.VISIBLE

        binding.availableLayout.availableRecyclerView.layoutManager =
            LinearLayoutManager(requireContext())
        val adapter = PayrollAdapter(list)
        binding.availableLayout.availableRecyclerView.adapter = adapter
    }

    // Dummy data for Available tab
    private fun getDummyAvailable(): List<Payroll> = listOf(
        Payroll(
            paymentMonth = "January",
            paymentId = "PMT001",
            status = Status.COMPLETED,
            noOfSalaries = 5,
            creationDate = "2025-01-01",
            paymentDate = "2025-01-05",
            amount = "5000"
        ),
        Payroll(
            paymentMonth = "February",
            paymentId = "PMT002",
            status = Status.PENDING,
            noOfSalaries = 3,
            creationDate = "2025-02-01",
            paymentDate = "2025-02-05",
            amount = "3000"
        ),
        Payroll(
            paymentMonth = "March",
            paymentId = "PMT003",
            status = Status.FAILED,
            noOfSalaries = 4,
            creationDate = "2025-03-01",
            paymentDate = "2025-03-05",
            amount = "4000"
        ),
        Payroll(
            paymentMonth = "April",
            paymentId = "PMT004",
            status = Status.REJECTED,
            noOfSalaries = 2,
            creationDate = "2025-04-01",
            paymentDate = "2025-04-05",
            amount = "2000"
        ),
        Payroll(
            paymentMonth = "May",
            paymentId = "PMT005",
            status = Status.CANCELED,
            noOfSalaries = 6,
            creationDate = "2025-05-01",
            paymentDate = "2025-05-05",
            amount = "6000"
        )

    )

    // Dummy data for History tab
    private fun getDummyHistory(): List<Payroll> = listOf(
        Payroll(
            paymentMonth = "January",
            paymentId = "PMT001",
            status = Status.COMPLETED,
            noOfSalaries = 5,
            creationDate = "2025-01-01",
            paymentDate = "2025-01-05",
            amount = "5000"
        ),
        Payroll(
            paymentMonth = "February",
            paymentId = "PMT002",
            status = Status.PENDING,
            noOfSalaries = 3,
            creationDate = "2025-02-01",
            paymentDate = "2025-02-05",
            amount = "3000"
        ),
        Payroll(
            paymentMonth = "March",
            paymentId = "PMT003",
            status = Status.FAILED,
            noOfSalaries = 4,
            creationDate = "2025-03-01",
            paymentDate = "2025-03-05",
            amount = "4000"
        ),
        Payroll(
            paymentMonth = "April",
            paymentId = "PMT004",
            status = Status.REJECTED,
            noOfSalaries = 2,
            creationDate = "2025-04-01",
            paymentDate = "2025-04-05",
            amount = "2000"
        ),
        Payroll(
            paymentMonth = "May",
            paymentId = "PMT005",
            status = Status.CANCELED,
            noOfSalaries = 6,
            creationDate = "2025-05-01",
            paymentDate = "2025-05-05",
            amount = "6000"
        )
    )

    private fun updateButtonForTab(tabType: String?) {
        when (tabType) {
            "Available" -> {
                binding.btnFilter.text = "Sort"
                binding.btnFilter.setIconResource(R.drawable.sorting_general) // your sorting icon
            }
            "History" -> {
                binding.btnFilter.text = "Filter"
                binding.btnFilter.setIconResource(R.drawable.filter) // your history icon
            }
        }
    }

}
