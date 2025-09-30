package com.example.wpsui.salary.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wpsui.R
import com.example.wpsui.salary.BankAccount

class BankAccountAdapter(
    val list: List<BankAccount>
): RecyclerView.Adapter<BankAccountAdapter.BankAccountViewHolder>() {

    inner class BankAccountViewHolder(item: View): RecyclerView.ViewHolder(item){
        val number = item.findViewById<TextView>(R.id.tvNumber)
        val accountName = item.findViewById<TextView>(R.id.tvAccountName)
        val amount = item.findViewById<TextView>(R.id.tvAmount)
        val employerName = item.findViewById<TextView>(R.id.tvEmployerName)
        val accountNumber = item.findViewById<TextView>(R.id.tvAccountNumber)
        val gcc = item.findViewById<TextView>(R.id.tvGcc)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BankAccountViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.salaries_layout, parent, false)
        return BankAccountViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: BankAccountViewHolder,
        position: Int
    ) {
        val data = list[position]
        with(holder){
            number.text = data.number.toString()
            accountName.text = data.accountHolderName
            amount.text = data.amount
            employerName.text = data.employerName
            accountNumber.text = data.accountNumber
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}