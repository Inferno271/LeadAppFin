package com.inferno271.leadappfin.ui.calls

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.inferno271.leadappfin.R
import com.inferno271.leadappfin.data.model.HouseData

class RecordDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record_details)

        // Получение данных о доме из Intent
        val intent = intent
        val houseData = intent?.getParcelableExtra<HouseData>("ID_KEY")

        // Нахождение каждого TextView по их идентификаторам
        val textContactName: TextView = findViewById(R.id.textContactName)
        val textPhoneNumber: TextView = findViewById(R.id.textPhoneNumber)
        val textRoomType: TextView = findViewById(R.id.textRoomType)
        val textOtherRoomDescription: TextView = findViewById(R.id.textOtherRoomDescription)
        val textAddress: TextView = findViewById(R.id.textAddress)
        val textLayout: TextView = findViewById(R.id.textLayout)
        val textSleepingPlaces: TextView = findViewById(R.id.textSleepingPlaces)
        val textCentralHeating: TextView = findViewById(R.id.textCentralHeating)
        val textAOGV: TextView = findViewById(R.id.textAogv)
        val textWarmFloor: TextView = findViewById(R.id.textWarmFloor)
        val textElectro: TextView = findViewById(R.id.textElectro)
        val textBoiler: TextView = findViewById(R.id.textBoiler)
        val textDeliveryTime: TextView = findViewById(R.id.textDeliveryTime)
        val textOtherDeliveryDescription: TextView = findViewById(R.id.textOtherDeliveryDescription)
        val textPrice: TextView = findViewById(R.id.textPrice)
        val textUtilities: TextView = findViewById(R.id.textUtilities)
        val textChildren: TextView = findViewById(R.id.textChildren)
        val textPets: TextView = findViewById(R.id.textPets)
        val textContractMilitary: TextView = findViewById(R.id.textContractMilitary)
        val textContractCompany: TextView = findViewById(R.id.textContractCompany)
        val textOtherInfo: TextView = findViewById(R.id.textOtherInfo)

        // Установка соответствующих значений в каждый TextView
        textContactName.text = "Contact Name: ${houseData?.contactName}"
        textPhoneNumber.text = "Phone Number: ${houseData?.phoneNumber}"
        textRoomType.text = "Room Type: ${houseData?.roomType}"
        textOtherRoomDescription.text = "Other Room Description: ${houseData?.otherRoomDescription ?: ""}"
        textAddress.text = "Address: ${houseData?.address}"
        textLayout.text = "Layout: ${houseData?.layout}"
        textSleepingPlaces.text = "Sleeping Places: ${houseData?.sleepingPlaces}"
        textCentralHeating.text = "Central Heating: ${houseData?.centralHeating}"
        textAOGV.text = "AOGV: ${houseData?.aogv}"
        textWarmFloor.text = "Warm Floor: ${houseData?.warmFloor}"
        textElectro.text = "Electro: ${houseData?.electro}"
        textBoiler.text = "Boiler: ${houseData?.boiler}"
        textDeliveryTime.text = "Delivery Time: ${houseData?.deliveryTime}"
        textOtherDeliveryDescription.text = "Other Delivery Description: ${houseData?.otherDeliveryDescription ?: ""}"
        textPrice.text = "Price: ${houseData?.price}"
        textUtilities.text = "Utilities: ${houseData?.utilities}"
        textChildren.text = "Children: ${houseData?.children}"
        textPets.text = "Pets: ${houseData?.pets}"
        textContractMilitary.text = "Contract Military: ${houseData?.contractMilitary}"
        textContractCompany.text = "Contract Company: ${houseData?.contractCompany}"
        textOtherInfo.text = "Other Info: ${houseData?.otherInfo ?: ""}"
    }
}
