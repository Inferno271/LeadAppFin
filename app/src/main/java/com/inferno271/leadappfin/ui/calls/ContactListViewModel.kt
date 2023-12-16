package com.inferno271.leadappfin.ui.calls

// ContactListViewModel.kt
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.inferno271.leadappfin.data.model.Contact

class ContactListViewModel : ViewModel() {

    private val _contactList = MutableLiveData<List<Contact>>()
    val contactList: LiveData<List<Contact>> get() = _contactList

    // Метод для загрузки списка контактов (замените его на вашу логику)
    fun loadContactList() {
        // Ваша логика получения списка контактов из базы данных или другого источника
        // Пример:
        val mockContactList = listOf(
            Contact(1, "John Doe", "+1234567890"),
            Contact(2, "Jane Smith", "+9876543210"),
            // Добавьте свои контакты
        )
        _contactList.value = mockContactList
    }
}
