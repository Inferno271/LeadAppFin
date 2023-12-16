package com.inferno271.leadappfin.ui.calls

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.inferno271.leadappfin.R
import com.inferno271.leadappfin.adapter.RecordAdapter
import com.inferno271.leadappfin.ui.adapter.RecordAdapter

// ContactDetailActivity.kt

// ... (imports and other code)

class ContactDetailActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecordAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_detail)

        recyclerView = findViewById(R.id.recyclerViewRecords)
        adapter = RecordAdapter(/* pass your data here */)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
}
