/* CallRepository.kt
package com.inferno271.leadappfin.data.repository

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.inferno271.leadappfin.data.database.DatabaseHelper
import com.inferno271.leadappfin.data.model.Call
import java.text.SimpleDateFormat
import java.util.*

class CallRepository(context: Context, private val databaseHelper: DatabaseHelper) {

    fun addCall(contactName: String?, phoneNumber: String, callDate: String) {
        val db = databaseHelper.writableDatabase
        val values = ContentValues().apply {
            put(DatabaseHelper.COLUMN_CONTACT_NAME, contactName)
            put(DatabaseHelper.COLUMN_PHONE_NUMBER, phoneNumber)
            put(DatabaseHelper.COLUMN_CALL_DATE, callDate)
        }

        try {
            db.insert(DatabaseHelper.TABLE_CALLS, null, values)
        } catch (e: SQLException) {
            Log.e("CallRepository", "Error adding call", e)
        } finally {
            db.close()
        }
    }

    fun getAllCalls(): List<Call> {
        val calls = mutableListOf<Call>()
        val db = databaseHelper.readableDatabase
        val query = "SELECT * FROM ${DatabaseHelper.TABLE_CALLS}"
        var cursor: Cursor? = null

        try {
            cursor = db.rawQuery(query, null)

            if (cursor.moveToFirst()) {
                val idColumnIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_ID)
                val contactNameColumnIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_CONTACT_NAME)
                val phoneNumberColumnIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_PHONE_NUMBER)
                val callDateColumnIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_CALL_DATE)

                do {
                    val id = cursor.getLong(idColumnIndex)
                    val contactName = if (contactNameColumnIndex != -1) cursor.getString(contactNameColumnIndex) else null
                    val phoneNumber = if (phoneNumberColumnIndex != -1) cursor.getString(phoneNumberColumnIndex) else ""
                    val callDate = if (callDateColumnIndex != -1) cursor.getString(callDateColumnIndex) else ""

                    calls.add(Call(id, contactName, phoneNumber, callDate, null, null, ""))
                } while (cursor.moveToNext())
            }
        } catch (e: SQLException) {
            Log.e("CallRepository", "Error getting calls", e)
            return emptyList()
        } finally {
            cursor?.close()
            db.close()
        }

        return calls
    }

    // Другие методы для взаимодействия с базой данных (например, удаление звонка и т.д.)
}
*/