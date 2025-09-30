package com.example.wpsui.filter

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.wpsui.R
import com.example.wpsui.databinding.ActivityFilterBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class FilterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFilterBinding
    private val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFilterBinding.inflate(layoutInflater)
        setContentView(binding.root)


        with(binding){
            tvStartDate.setOnClickListener {
                showDatePicker(it as EditText)
            }

            tvFinishDate.setOnClickListener {
                showDatePicker(it as EditText)
            }


            btnApply.setOnClickListener {
                val nameSort = when (rgName.checkedRadioButtonId) {
                    R.id.rbAZ -> "A-Z"
                    R.id.rbZA -> "Z-A"
                    else -> ""
                }

                val amountSort = when (rgAmount.checkedRadioButtonId) {
                    R.id.rbLowHigh -> "Low to High"
                    R.id.rbHighLow -> "High to Low"
                    else -> ""
                }

                val startDate = tvStartDate.text.toString()
                val finishDate = tvFinishDate.text.toString()
                val minAmount = etMinAmount.text.toString()
                val maxAmount = etMaxAmount.text.toString()

                Toast.makeText(
                    this@FilterActivity,
                    "Filter Applied:\nName: $nameSort\nAmount: $amountSort\nDates: $startDate - $finishDate\nRange: $minAmount - $maxAmount",
                    Toast.LENGTH_LONG
                ).show()
            }

            tvCancel.setOnClickListener {

            }
        }

    }


    private fun showDatePicker(editText: EditText) {
        val calendar = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                calendar.set(year, month, dayOfMonth)
                editText.setText(dateFormatter.format(calendar.time))
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        datePickerDialog.datePicker.maxDate = System.currentTimeMillis()


        if (editText.id == R.id.tvFinishDate) {
            val startDateText = binding.tvStartDate.text.toString()
            if (startDateText.isNotEmpty()) {
                try {
                    val startDate = dateFormatter.parse(startDateText)
                    startDate?.let {
                        datePickerDialog.datePicker.minDate = it.time
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

        datePickerDialog.show()
    }
}