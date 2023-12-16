package com.inferno271.leadappfin.data.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.inferno271.leadappfin.data.model.HouseData

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "HouseDataDB"
        private const val TABLE_NAME = "house_data"

        // Колонки таблицы
        private const val KEY_ID = "id"
        private const val KEY_CONTACT_NAME = "contact_name"
        private const val KEY_PHONE_NUMBER = "phone_number"
        private const val KEY_ROOM_TYPE = "room_type"
        private const val KEY_OTHER_ROOM_DESCRIPTION = "other_room_description"
        private const val KEY_ADDRESS = "address"
        private const val KEY_LAYOUT = "layout"
        private const val KEY_SLEEPING_PLACES = "sleeping_places"
        private const val KEY_CENTRAL_HEATING = "central_heating"
        private const val KEY_AOGV = "aogv"
        private const val KEY_WARM_FLOOR = "warm_floor"
        private const val KEY_ELECTRO = "electro"
        private const val KEY_BOILER = "boiler"
        private const val KEY_DELIVERY_TIME = "delivery_time"
        private const val KEY_OTHER_DELIVERY_DESCRIPTION = "other_delivery_description"
        private const val KEY_PRICE = "price"
        private const val KEY_UTILITIES = "utilities"
        private const val KEY_CHILDREN = "children"
        private const val KEY_PETS = "pets"
        private const val KEY_CONTRACT_MILITARY = "contract_military"
        private const val KEY_CONTRACT_COMPANY = "contract_company"
        private const val KEY_OTHER_INFO = "other_info"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = ("CREATE TABLE $TABLE_NAME ($KEY_ID INTEGER PRIMARY KEY, "
                + "$KEY_CONTACT_NAME TEXT, "
                + "$KEY_PHONE_NUMBER TEXT, "
                + "$KEY_ROOM_TYPE TEXT, "
                + "$KEY_OTHER_ROOM_DESCRIPTION TEXT, "
                + "$KEY_ADDRESS TEXT, "
                + "$KEY_LAYOUT TEXT, "
                + "$KEY_SLEEPING_PLACES TEXT, "
                + "$KEY_CENTRAL_HEATING INTEGER, "
                + "$KEY_AOGV INTEGER, "
                + "$KEY_WARM_FLOOR INTEGER, "
                + "$KEY_ELECTRO INTEGER, "
                + "$KEY_BOILER INTEGER, "
                + "$KEY_DELIVERY_TIME TEXT, "
                + "$KEY_OTHER_DELIVERY_DESCRIPTION TEXT, "
                + "$KEY_PRICE TEXT, "
                + "$KEY_UTILITIES TEXT, "
                + "$KEY_CHILDREN TEXT, "
                + "$KEY_PETS TEXT, "
                + "$KEY_CONTRACT_MILITARY INTEGER, "
                + "$KEY_CONTRACT_COMPANY INTEGER, "
                + "$KEY_OTHER_INFO TEXT)")
        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addHouseData(houseData: HouseData): Long {
        val values = ContentValues().apply {
            put(KEY_CONTACT_NAME, houseData.contactName)
            put(KEY_PHONE_NUMBER, houseData.phoneNumber)
            put(KEY_ROOM_TYPE, houseData.roomType)
            put(KEY_OTHER_ROOM_DESCRIPTION, houseData.otherRoomDescription)
            put(KEY_ADDRESS, houseData.address)
            put(KEY_LAYOUT, houseData.layout)
            put(KEY_SLEEPING_PLACES, houseData.sleepingPlaces)
            put(KEY_CENTRAL_HEATING, if (houseData.centralHeating) 1 else 0)
            put(KEY_AOGV, if (houseData.aogv) 1 else 0)
            put(KEY_WARM_FLOOR, if (houseData.warmFloor) 1 else 0)
            put(KEY_ELECTRO, if (houseData.electro) 1 else 0)
            put(KEY_BOILER, if (houseData.boiler) 1 else 0)
            put(KEY_DELIVERY_TIME, houseData.deliveryTime)
            put(KEY_OTHER_DELIVERY_DESCRIPTION, houseData.otherDeliveryDescription)
            put(KEY_PRICE, houseData.price)
            put(KEY_UTILITIES, houseData.utilities)
            put(KEY_CHILDREN, houseData.children)
            put(KEY_PETS, houseData.pets)
            put(KEY_CONTRACT_MILITARY, if (houseData.contractMilitary) 1 else 0)
            put(KEY_CONTRACT_COMPANY, if (houseData.contractCompany) 1 else 0)
            put(KEY_OTHER_INFO, houseData.otherInfo)
        }

        val db = writableDatabase
        val id = db.insert(TABLE_NAME, null, values)
        db.close()
        return id
    }

    fun getAllHouseData(): List<HouseData> {
        val houseDataList = mutableListOf<HouseData>()
        val selectQuery = "SELECT * FROM $TABLE_NAME"
        val db = readableDatabase
        val cursor = db.rawQuery(selectQuery, null)

        if (cursor.moveToFirst()) {
            do {
                val houseData = HouseData(
                    cursor.getLong(cursor.getColumnIndex(KEY_ID)),
                    cursor.getString(cursor.getColumnIndex(KEY_CONTACT_NAME)),
                    cursor.getString(cursor.getColumnIndex(KEY_PHONE_NUMBER)),
                    cursor.getString(cursor.getColumnIndex(KEY_ROOM_TYPE)),
                    cursor.getString(cursor.getColumnIndex(KEY_OTHER_ROOM_DESCRIPTION)),
                    cursor.getString(cursor.getColumnIndex(KEY_ADDRESS)),
                    cursor.getString(cursor.getColumnIndex(KEY_LAYOUT)),
                    cursor.getString(cursor.getColumnIndex(KEY_SLEEPING_PLACES)),
                    cursor.getInt(cursor.getColumnIndex(KEY_CENTRAL_HEATING)) != 0,
                    cursor.getInt(cursor.getColumnIndex(KEY_AOGV)) != 0,
                    cursor.getInt(cursor.getColumnIndex(KEY_WARM_FLOOR)) != 0,
                    cursor.getInt(cursor.getColumnIndex(KEY_ELECTRO)) != 0,
                    cursor.getInt(cursor.getColumnIndex(KEY_BOILER)) != 0,
                    cursor.getString(cursor.getColumnIndex(KEY_DELIVERY_TIME)),
                    cursor.getString(cursor.getColumnIndex(KEY_OTHER_DELIVERY_DESCRIPTION)),
                    cursor.getString(cursor.getColumnIndex(KEY_PRICE)),
                    cursor.getString(cursor.getColumnIndex(KEY_UTILITIES)),
                    cursor.getString(cursor.getColumnIndex(KEY_CHILDREN)),
                    cursor.getString(cursor.getColumnIndex(KEY_PETS)),
                    cursor.getInt(cursor.getColumnIndex(KEY_CONTRACT_MILITARY)) != 0,
                    cursor.getInt(cursor.getColumnIndex(KEY_CONTRACT_COMPANY)) != 0,
                    cursor.getString(cursor.getColumnIndex(KEY_OTHER_INFO))
                )
                houseDataList.add(houseData)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return houseDataList
    }


// Добавьте метод для извлечения Long из столбца
    private fun getLongFromColumnName(cursor: Cursor, columnName: String): Long {
        val columnIndex = cursor.getColumnIndex(columnName)
        return if (columnIndex != -1) cursor.getLong(columnIndex) else -1
    }


    private fun getStringFromColumnName(cursor: Cursor, columnName: String): String {
        val columnIndex = cursor.getColumnIndex(columnName)
        return if (columnIndex != -1) cursor.getString(columnIndex) else ""
    }

    private fun getBooleanFromColumnName(cursor: Cursor, columnName: String): Boolean {
        val columnIndex = cursor.getColumnIndex(columnName)
        return columnIndex != -1 && cursor.getInt(columnIndex) == 1
    }



}
