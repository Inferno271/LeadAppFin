// RecordAdapter.kt
package com.inferno271.leadappfin.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.inferno271.leadappfin.R
import com.inferno271.leadappfin.data.model.Record

class RecordAdapter : ListAdapter<Record, RecordAdapter.RecordViewHolder>(RecordDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.record_item, parent, false)
        return RecordViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecordViewHolder, position: Int) {
        val record = getItem(position)
        holder.bind(record)
    }

    class RecordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textContactName: TextView = itemView.findViewById(R.id.textContactName)
        private val textPhoneNumber: TextView = itemView.findViewById(R.id.textPhoneNumber)

        fun bind(record: Record) {
            textContactName.text = record.contactName
            textPhoneNumber.text = record.phoneNumber

            // Добавьте дополнительные привязки данных, если необходимо
        }
    }

    private class RecordDiffCallback : DiffUtil.ItemCallback<Record>() {
        override fun areItemsTheSame(oldItem: Record, newItem: Record): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Record, newItem: Record): Boolean {
            return oldItem == newItem
        }
    }
}
