package com.example.wpsui

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.wpsui.databinding.FragmentStatusStateBinding

class StatusStateFragment : Fragment() {
    private lateinit var binding: FragmentStatusStateBinding

    private lateinit var payroll: Payroll

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStatusStateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            payroll = StatusStateFragmentArgs.fromBundle(it).payroll
        }

        setPayrollData()
    }

    private fun setPayrollData() {
        binding.tvPayrollTitle.text = "${payroll.paymentMonth}"

        binding.tvPayrollNumber.text = payroll.paymentId

        val amountParts = payroll.amount.split(".")
        binding.tvCurrency.text = getString(R.string.bhd)  // Assuming R.string.bhd is "BHD"

        if (amountParts.size > 1) {
            binding.tvAmount.text = amountParts[0]
            binding.tvDecimal.text = amountParts[1]
        } else {
            binding.tvAmount.text = amountParts[0]
            binding.tvDecimal.text = "000"
        }

        binding.tvStatusValue.text = payroll.status.name

        binding.tvStatusValue.setTextColor(
            when (payroll.status) {
                Status.COMPLETED -> ContextCompat.getColor(context, R.color.green)
                Status.REJECTED -> ContextCompat.getColor(
                    context,
                    R.color.status_rejected_text
                )

                Status.FAILED -> ContextCompat.getColor(
                    context,
                    R.color.status_failed_text
                )

                Status.CANCELED -> ContextCompat.getColor(
                    context,
                    R.color.status_canceled_text
                )

                Status.EXPIRED -> ContextCompat.getColor(
                    context,
                    R.color.status_expired_text
                )

                Status.PENDING -> ContextCompat.getColor(
                    context,
                    R.color.status_pending_text
                )

                Status.AUTHORIZED -> ContextCompat.getColor(
                    context,
                    R.color.status_authorized_text
                )
            }
        )


        val drawable = GradientDrawable().apply {
            cornerRadius = 12f
            setColor(

                when (payroll.status) {
                    Status.COMPLETED -> ContextCompat.getColor(
                        context,
                        R.color.light_green
                    )

                    Status.PENDING -> ContextCompat.getColor(
                        context,
                        R.color.status_pending
                    )

                    Status.REJECTED -> ContextCompat.getColor(
                        context,
                        R.color.status_rejected
                    )

                    Status.FAILED -> ContextCompat.getColor(
                        context,
                        R.color.status_failed
                    )

                    Status.CANCELED -> ContextCompat.getColor(
                        context,
                        R.color.status_canceled
                    )

                    Status.EXPIRED -> ContextCompat.getColor(
                        context,
                        R.color.status_expired
                    )

                    Status.AUTHORIZED -> ContextCompat.getColor(
                        context,
                        R.color.status_authorized
                    )

                    else -> ContextCompat.getColor(context, R.color.status_default)
                }
            )
        }

        binding.tvStatusValue.background = drawable

        binding.tvCreationDateValue.text = payroll.creationDate

        binding.tvPaymentDateValue.text = payroll.paymentDate

        binding.tvEmployerName.text = "Mohammed Ahmed Abdulla Al-Baloshi"  // This could be part of the payroll object as well.

        binding.tvDescriptionPlaceholder.text = getString(R.string.optional)


        if(payroll.status== Status.AUTHORIZED){
            binding.llAuthorizePayment.visibility = View.GONE
            binding.llCancelPayment.visibility = View.VISIBLE
        }else if(payroll.status== Status.PENDING){
            binding.llAuthorizePayment.visibility = View.VISIBLE
            binding.llCancelPayment.visibility = View.GONE
        }else{
            binding.llAuthorizePayment.visibility = View.GONE
            binding.llCancelPayment.visibility = View.GONE
        }

    }

}
