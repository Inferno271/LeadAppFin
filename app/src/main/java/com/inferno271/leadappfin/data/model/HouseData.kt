// HouseData.kt
package com.inferno271.leadappfin.data.model

import android.os.Parcel
import android.os.Parcelable

data class HouseData(
    val id: Long, // Идентификатор записи
    val contactName: String?,
    val phoneNumber: String?,
    val roomType: String?,
    val otherRoomDescription: String?,
    val address: String?,
    val layout: String?,
    val sleepingPlaces: String?,
    val centralHeating: Boolean,
    val aogv: Boolean,
    val warmFloor: Boolean,
    val electro: Boolean,
    val boiler: Boolean,
    val deliveryTime: String?,
    val otherDeliveryDescription: String?,
    val price: String?,
    val utilities: String?,
    val children: String?,
    val pets: String?,
    val contractMilitary: Boolean,
    val contractCompany: Boolean,
    val otherInfo: String?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(contactName)
        parcel.writeString(phoneNumber)
        parcel.writeString(roomType)
        parcel.writeString(otherRoomDescription)
        parcel.writeString(address)
        parcel.writeString(layout)
        parcel.writeString(sleepingPlaces)
        parcel.writeByte(if (centralHeating) 1 else 0)
        parcel.writeByte(if (aogv) 1 else 0)
        parcel.writeByte(if (warmFloor) 1 else 0)
        parcel.writeByte(if (electro) 1 else 0)
        parcel.writeByte(if (boiler) 1 else 0)
        parcel.writeString(deliveryTime)
        parcel.writeString(otherDeliveryDescription)
        parcel.writeString(price)
        parcel.writeString(utilities)
        parcel.writeString(children)
        parcel.writeString(pets)
        parcel.writeByte(if (contractMilitary) 1 else 0)
        parcel.writeByte(if (contractCompany) 1 else 0)
        parcel.writeString(otherInfo)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<HouseData> {
        override fun createFromParcel(parcel: Parcel): HouseData {
            return HouseData(parcel)
        }

        override fun newArray(size: Int): Array<HouseData?> {
            return arrayOfNulls(size)
        }
    }
}
