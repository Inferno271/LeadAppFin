// Record.kt
package com.inferno271.leadappfin.data.model

data class Record(
    val id: Long, // Идентификатор записи
    val contactName: String,
    val phoneNumber: String,
    val roomType: String,
    val otherRoomDescription: String?,
    val address: String,
    val layout: String,
    val sleepingPlaces: String,
    val centralHeating: Boolean,
    val aogv: Boolean,
    val warmFloor: Boolean,
    val electro: Boolean,
    val boiler: Boolean,
    val deliveryTime: String,
    val otherDeliveryDescription: String?,
    val price: String,
    val utilities: String,
    val children: String,
    val pets: String,
    val contractMilitary: Boolean,
    val contractCompany: Boolean,
    val otherInfo: String
)
