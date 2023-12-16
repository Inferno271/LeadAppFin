package com.inferno271.leadappfin.ui.adapter

// ContactListAdapter.kt
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.inferno271.leadappfin.R
import com.inferno271.leadappfin.data.model.Contact

class ContactListAdapter(private val contactClickListener: (Contact) -> Unit) :
    RecyclerView.Adapter<ContactListAdapter.ContactViewHolder>() {

    private var contactList: List<Contact> = emptyList()

    fun setContactList(contactList: List<Contact>) {
        this.contactList = contactList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.contact_list_item, parent, false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(contactList[position])
    }

    override fun getItemCount(): Int = contactList.size

    inner class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val contactNameTextView: TextView = itemView.findViewById(R.id.textContactName)
        private val phoneNumberTextView: TextView = itemView.findViewById(R.id.textPhoneNumber)

        fun bind(contact: Contact) {
            contactNameTextView.text = contact.contactName
            phoneNumberTextView.text = contact.phoneNumber

            itemView.setOnClickListener { contactClickListener.invoke(contact) }
        }
    }
}
