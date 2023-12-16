package com.inferno271.leadappfin.ui

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.inferno271.leadappfin.R
import com.inferno271.leadappfin.data.model.Record
import com.inferno271.leadappfin.ui.adapter.RecordAdapter

class ContactCardActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recordAdapter: RecordAdapter // Предполагается, что у вас уже есть адаптер для записей

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_card)

        // Получите данные из Intent
        val contactName: String? = intent.getStringExtra("contactName")
        val phoneNumber: String? = intent.getStringExtra("phoneNumber")

        // Инициализация компонентов из макета
        val textContactNameHeader = findViewById<TextView>(R.id.textContactNameHeader)
        val textPhoneNumberHeader = findViewById<TextView>(R.id.textPhoneNumberHeader)
        recyclerView = findViewById(R.id.recyclerViewRecords)

        // Установите данные в шапке
        textContactNameHeader.text = contactName
        textPhoneNumberHeader.text = phoneNumber

        // Инициализация RecyclerView и его адаптера
        recordAdapter = RecordAdapter() // Здесь используйте ваш собственный адаптер
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = recordAdapter

        // Здесь добавьте код для загрузки записей из базы данных или другого источника данных
        val records = getRecordsForContact(contactName, phoneNumber)
        recordAdapter.submitList(records)
    }

    // Здесь добавьте метод для получения записей для конкретного контакта из базы данных
    private fun getRecordsForContact(contactName: String?, phoneNumber: String?): List<Record> {
        // Реализуйте загрузку записей из базы данных или другого источника данных
        // Возвращайте список записей
        return emptyList() // или возвращайте реальные записи из вашего источника данных
    }
}
