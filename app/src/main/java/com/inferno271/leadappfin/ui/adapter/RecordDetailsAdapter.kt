// RecordDetailsAdapter.kt
package com.inferno271.leadappfin.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.inferno271.leadappfin.R
import com.inferno271.leadappfin.data.model.HouseData

class RecordDetailsAdapter(private val context: Context, private val houseData: HouseData) : BaseAdapter() {

    override fun getCount(): Int {
        return 1 // В данном случае у нас всегда одна запись
    }

    override fun getItem(position: Int): Any {
        return houseData
    }

    override fun getItemId(position: Int): Long {
        return houseData.id
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = convertView ?: inflater.inflate(R.layout.activity_record_details, null)

        // Находим все TextView в макете
        val textContactName: TextView = view.findViewById(R.id.textContactName)
        val textPhoneNumber: TextView = view.findViewById(R.id.textPhoneNumber)
        val textRoomType: TextView = view.findViewById(R.id.textRoomType)
        val textOtherRoomDescription: TextView = view.findViewById(R.id.textOtherRoomDescription)
        val textAddress: TextView = view.findViewById(R.id.textAddress)
        val textLayout: TextView = view.findViewById(R.id.textLayout)
        val textSleepingPlaces: TextView = view.findViewById(R.id.textSleepingPlaces)
        val textCentralHeating: TextView = view.findViewById(R.id.textCentralHeating)
        val textAOGV: TextView = view.findViewById(R.id.textAogv)
        val textWarmFloor: TextView = view.findViewById(R.id.textWarmFloor)
        val textElectro: TextView = view.findViewById(R.id.textElectro)
        val textBoiler: TextView = view.findViewById(R.id.textBoiler)
        val textDeliveryTime: TextView = view.findViewById(R.id.textDeliveryTime)
        val textOtherDeliveryDescription: TextView = view.findViewById(R.id.textOtherDeliveryDescription)
        val textPrice: TextView = view.findViewById(R.id.textPrice)
        val textUtilities: TextView = view.findViewById(R.id.textUtilities)
        val textChildren: TextView = view.findViewById(R.id.textChildren)
        val textPets: TextView = view.findViewById(R.id.textPets)
        val textContractMilitary: TextView = view.findViewById(R.id.textContractMilitary)
        val textContractCompany: TextView = view.findViewById(R.id.textContractCompany)
        val textOtherInfo: TextView = view.findViewById(R.id.textOtherInfo)

        // Устанавливаем значения в TextView
        textContactName.text = "Contact Name: ${houseData.contactName}"
        textPhoneNumber.text = "Phone Number: ${houseData.phoneNumber}"
        textRoomType.text = "Room Type: ${houseData.roomType}"
        textOtherRoomDescription.text = "Other Room Description: ${houseData.otherRoomDescription ?: ""}"
        textAddress.text = "Address: ${houseData.address}"
        textLayout.text = "Layout: ${houseData.layout}"
        textSleepingPlaces.text = "Sleeping Places: ${houseData.sleepingPlaces}"
        textCentralHeating.text = "Central Heating: ${houseData.centralHeating}"
        textAOGV.text = "AOGV: ${houseData.aogv}"
        textWarmFloor.text = "Warm Floor: ${houseData.warmFloor}"
        textElectro.text = "Electro: ${houseData.electro}"
        textBoiler.text = "Boiler: ${houseData.boiler}"
        textDeliveryTime.text = "Delivery Time: ${houseData.deliveryTime}"
        textOtherDeliveryDescription.text = "Other Delivery Description: ${houseData.otherDeliveryDescription ?: ""}"
        textPrice.text = "Price: ${houseData.price}"
        textUtilities.text = "Utilities: ${houseData.utilities}"
        textChildren.text = "Children: ${houseData.children}"
        textPets.text = "Pets: ${houseData.pets}"
        textContractMilitary.text = "Contract Military: ${houseData.contractMilitary}"
        textContractCompany.text = "Contract Company: ${houseData.contractCompany}"
        textOtherInfo.text = "Other Info: ${houseData.otherInfo ?: ""}"

        return view
    }
}
