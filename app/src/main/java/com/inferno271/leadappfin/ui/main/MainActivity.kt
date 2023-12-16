// MainActivity.kt
package com.inferno271.leadappfin.ui.main

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.inferno271.leadappfin.R
import com.inferno271.leadappfin.data.database.DatabaseHelper
import com.inferno271.leadappfin.ui.adapter.ContactAdapter
import com.inferno271.leadappfin.ui.adapter.ContactListAdapter
import com.inferno271.leadappfin.ui.service.CallHandlingService

class MainActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var adapter: ContactListAdapter
    private lateinit var dbHelper: DatabaseHelper

    private val permissions = arrayOf(
        Manifest.permission.READ_PHONE_STATE,
        Manifest.permission.CALL_PHONE,
        Manifest.permission.READ_CONTACTS,
        Manifest.permission.SYSTEM_ALERT_WINDOW,
        Manifest.permission.READ_CALL_LOG
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_list)

        listView = findViewById(R.id.listViewRecordDetails)

        listView = findViewById(R.id.listViewRecordDetails)
        dbHelper = DatabaseHelper(this)
        loadContactData()

        // Проверка и запрос разрешений, если необходимо
        if (!arePermissionsGranted()) {
            requestPermissions()
        } else {
            // loadCallListFragment()
            startCallHandlingService()
        }
    }

    private fun arePermissionsGranted(): Boolean {
        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(this, permissions, PERMISSIONS_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == PERMISSIONS_REQUEST_CODE && grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
            loadCallListFragment()
        } else {
            // Обработка случая, когда пользователь не предоставил необходимые разрешения
            // Можете добавить здесь дополнительную логику или предложить пользователю предоставить разрешения в настройках приложения
        }
    }

    private fun startCallHandlingService() {
        val serviceIntent = Intent(this, CallHandlingService::class.java)
        startService(serviceIntent)
    }

    private fun loadContactData() {
        val contactList = dbHelper.getAllContacts() // Замените этим методом получение списка контактов из базы данных
        adapter = ContactListAdapter(this, R.layout.contact_list_item, contactList)
        listView.adapter = adapter

        listView.setOnItemClickListener { parent, view, position, id ->
            val contact = adapter.getItem(position)
            // Действия при нажатии на элемент списка, например, открыть детальную информацию о контакте
        }
    }

    companion object {
        private const val PERMISSIONS_REQUEST_CODE = 123
    }
}
