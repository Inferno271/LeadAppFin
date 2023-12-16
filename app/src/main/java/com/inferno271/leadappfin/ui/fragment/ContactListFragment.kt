package com.inferno271.leadappfin.ui.fragment

// ContactListFragment.kt
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.inferno271.leadappfin.R
import com.inferno271.leadappfin.ui.adapter.ContactListAdapter
import com.inferno271.leadappfin.data.model.Contact
import com.inferno271.leadappfin.ui.calls.ContactListViewModel

class ContactListFragment : Fragment() {

    private lateinit var viewModel: ContactListViewModel
    private lateinit var contactListAdapter: ContactListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_contact_list, container, false)

        // Инициализация ViewModel
        viewModel = ViewModelProvider(this).get(ContactListViewModel::class.java)

        // Инициализация RecyclerView и адаптера
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewContacts)
        contactListAdapter = ContactListAdapter { contact -> onContactClicked(contact) }
        recyclerView.adapter = contactListAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Загрузка списка контактов
        viewModel.loadContactList()

        // Наблюдение за изменениями в списке контактов
        viewModel.contactList.observe(viewLifecycleOwner, { contacts ->
            contactListAdapter.setContactList(contacts)
        })

        return view
    }

    private fun onContactClicked(contact: Contact) {
        // Обработка нажатия на контакт
        // Вызов нужного фрагмента или активности для отображения деталей контакта
        // (например, открытие ContactDetailActivity)
    }
}
