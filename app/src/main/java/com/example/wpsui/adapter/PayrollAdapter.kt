package com.example.wpsui.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.wpsui.Payroll
import com.example.wpsui.R
import com.example.wpsui.Status
import kotlin.with

class PayrollAdapter(
    private var list: List<Payroll>
) : RecyclerView.Adapter<PayrollAdapter.PayrollViewHolder>() {

    // Track the currently selected item position
    private var selectedPosition = RecyclerView.NO_POSITION

    inner class PayrollViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val card = itemView.findViewById<CardView>(R.id.payrollCard)
        val paymentMonth = itemView.findViewById<TextView>(R.id.tvPayrollTitle)
        val paymentId = itemView.findViewById<TextView>(R.id.tvPayrollRef)
        val status = itemView.findViewById<TextView>(R.id.tvStatus)
        val noOfSalaries = itemView.findViewById<TextView>(R.id.tvNoOfSalaries)
        val creationDate = itemView.findViewById<TextView>(R.id.tvCreationDate)
        val paymentDate = itemView.findViewById<TextView>(R.id.tvPaymentDate)
        val amount = itemView.findViewById<TextView>(R.id.tvAmount)

        val payrollBtn = itemView.findViewById<TextView>(R.id.btnReviewPayment)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PayrollViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.payroll_layout, parent, false)
        return PayrollViewHolder(view)
    }

    override fun onBindViewHolder(holder: PayrollViewHolder, position: Int) {
        val item = list[position]

        with(holder) {
            paymentMonth.text = item.paymentMonth
            paymentId.text = item.paymentId

            status.setBackgroundColor(
                when (item.status) {
                    Status.COMPLETED -> ContextCompat.getColor(
                        itemView.context,
                        R.color.light_green
                    )

                    Status.PENDING -> ContextCompat.getColor(
                        itemView.context,
                        R.color.status_pending
                    )

                    Status.REJECTED -> ContextCompat.getColor(
                        itemView.context,
                        R.color.status_rejected
                    )

                    Status.FAILED -> ContextCompat.getColor(itemView.context, R.color.status_failed)
                    Status.CANCELED -> ContextCompat.getColor(
                        itemView.context,
                        R.color.status_canceled
                    )

                    Status.EXPIRED -> ContextCompat.getColor(
                        itemView.context,
                        R.color.status_expired
                    )

                    Status.AUTHORIZED -> ContextCompat.getColor(
                        itemView.context,
                        R.color.status_authorized
                    )

                    else -> ContextCompat.getColor(itemView.context, R.color.status_default)
                }
            )

            status.setTextColor(
                when (item.status) {
                    Status.COMPLETED -> ContextCompat.getColor(itemView.context, R.color.green)
                    Status.REJECTED -> ContextCompat.getColor(
                        itemView.context,
                        R.color.status_rejected_text
                    )

                    Status.FAILED -> ContextCompat.getColor(
                        itemView.context,
                        R.color.status_failed_text
                    )

                    Status.CANCELED -> ContextCompat.getColor(
                        itemView.context,
                        R.color.status_canceled_text
                    )

                    Status.EXPIRED -> ContextCompat.getColor(
                        itemView.context,
                        R.color.status_expired_text
                    )

                    Status.PENDING -> ContextCompat.getColor(
                        itemView.context,
                        R.color.status_pending_text
                    )

                    Status.AUTHORIZED -> ContextCompat.getColor(
                        itemView.context,
                        R.color.status_authorized_text
                    )
                }
            )
            status.text = item.status.toString()
            noOfSalaries.text = item.noOfSalaries.toString()
            creationDate.text = item.creationDate
            paymentDate.text = item.paymentDate
            amount.text = item.amount

            val drawable = GradientDrawable().apply {
                cornerRadius = 12f
                setColor(

                    when (item.status) {
                        Status.COMPLETED -> ContextCompat.getColor(
                            itemView.context,
                            R.color.light_green
                        )

                        Status.PENDING -> ContextCompat.getColor(
                            itemView.context,
                            R.color.status_pending
                        )

                        Status.REJECTED -> ContextCompat.getColor(
                            itemView.context,
                            R.color.status_rejected
                        )

                        Status.FAILED -> ContextCompat.getColor(
                            itemView.context,
                            R.color.status_failed
                        )

                        Status.CANCELED -> ContextCompat.getColor(
                            itemView.context,
                            R.color.status_canceled
                        )

                        Status.EXPIRED -> ContextCompat.getColor(
                            itemView.context,
                            R.color.status_expired
                        )

                        Status.AUTHORIZED -> ContextCompat.getColor(
                            itemView.context,
                            R.color.status_authorized
                        )

                        else -> ContextCompat.getColor(itemView.context, R.color.status_default)
                    }
                ) // dynamic based on status
            }
            holder.status.background = drawable

            // Show button only if this item is selected
            payrollBtn.visibility = if (position == selectedPosition) View.VISIBLE else View.GONE

            card.setOnClickListener {
                // Update selected position
                val previousPosition = selectedPosition
                selectedPosition =
                    if (selectedPosition == position) RecyclerView.NO_POSITION else position

                // Notify previous and current positions to refresh UI
                if (previousPosition != RecyclerView.NO_POSITION) notifyItemChanged(previousPosition)
                notifyItemChanged(position)
            }
        }
    }

    override fun getItemCount(): Int = list.size

    fun updateList(newList: List<Payroll>) {
        list = newList
        notifyDataSetChanged()
    }
}
