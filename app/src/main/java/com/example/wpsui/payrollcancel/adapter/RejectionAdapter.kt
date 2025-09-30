package com.example.wpsui.payrollcancel.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wpsui.R

class RejectionAdapter (
    val list: List<String>,
    private val onItemSelected: (String) -> Unit
): RecyclerView.Adapter<RejectionAdapter.RejectionViewHolder>() {

    private var selectedPosition = -1
    inner class RejectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val radioReason = itemView.findViewById<RadioButton>(R.id.radioReason)

        init {
            radioReason.setOnClickListener {
                val oldPos = selectedPosition
                selectedPosition = adapterPosition

                if (oldPos != -1) notifyItemChanged(oldPos)
                notifyItemChanged(selectedPosition)

                onItemSelected(list[selectedPosition])
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RejectionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rejection_reason, parent, false)
        return RejectionViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: RejectionViewHolder,
        position: Int
    ) {
        val data = list[position]
        with(holder){
            radioReason.text = data
            radioReason.isChecked = position == selectedPosition
        }
//        holder.radioReason.text = data
//        holder.radioReason.isChecked = position == selectedPosition
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun getSelectedReason(): String? {
        return if (selectedPosition != -1) list[selectedPosition] else null
    }

}