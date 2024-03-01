// src/main/java/com/example/userprofile/ContactLogAdapter.kt
package com.example.userprofile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactLogAdapter(private val contactLogList: List<ContactLogItem>) :
    RecyclerView.Adapter<ContactLogAdapter.ContactLogViewHolder>() {

    class ContactLogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvContactName: TextView = itemView.findViewById(R.id.tvContactName)
        val tvContactDate: TextView = itemView.findViewById(R.id.tvContactDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactLogViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_contact_log, parent, false)
        return ContactLogViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ContactLogViewHolder, position: Int) {
        val currentItem = contactLogList[position]

        holder.tvContactName.text = "Name: ${currentItem.contactName}"
        holder.tvContactDate.text = "Date: ${currentItem.contactDate}"
    }

    override fun getItemCount() = contactLogList.size
}
