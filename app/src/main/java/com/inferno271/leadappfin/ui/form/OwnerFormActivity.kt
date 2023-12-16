package com.inferno271.leadappfin.ui.form


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.inferno271.leadappfin.R
import com.inferno271.leadappfin.data.database.DatabaseHelper
import com.inferno271.leadappfin.data.model.HouseData
import com.inferno271.leadappfin.ui.main.MainActivity


class OwnerFormActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper


    private lateinit var contactNameEditText: EditText
    private lateinit var phoneNumberValueTextView: TextView
    private lateinit var radioGroupRooms: RadioGroup
    private lateinit var otherRoomEditText: EditText
    private lateinit var addressEditText: EditText
    private lateinit var spinnerLayout: Spinner
    private lateinit var sleepingPlacesEditText: EditText
    private lateinit var checkCentralHeating: CheckBox
    private lateinit var checkAOGV: CheckBox
    private lateinit var checkWarmFloor: CheckBox
    private lateinit var checkElectro: CheckBox
    private lateinit var checkBoiler: CheckBox
    private lateinit var radioGroupDelivery: RadioGroup
    private lateinit var otherDeliveryEditText: EditText
    private lateinit var editPrice: EditText
    private lateinit var spinnerUtilities: Spinner
    private lateinit var spinnerChildren: Spinner
    private lateinit var spinnerPets: Spinner
    private lateinit var checkContractMilitary: CheckBox
    private lateinit var checkContractCompany: CheckBox
    private lateinit var editOther: EditText
    private lateinit var buttonSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.owner_form_layout)

        // Получите данные из Intent
        val contactName = intent.getStringExtra("contactName")
        val phoneNumber = intent.getStringExtra("phoneNumber")



        // Инициализация компонентов из макета
        contactNameEditText = findViewById(R.id.editContactName)
        phoneNumberValueTextView = findViewById(R.id.textPhoneNumberValue)
        radioGroupRooms = findViewById(R.id.radioGroupRooms)
        otherRoomEditText = findViewById(R.id.editOtherRoom)
        addressEditText = findViewById(R.id.editAddress)
        spinnerLayout = findViewById(R.id.spinnerLayout)
        sleepingPlacesEditText = findViewById(R.id.editSleepingPlaces)
        checkCentralHeating = findViewById(R.id.checkCentralHeating)
        checkAOGV = findViewById(R.id.checkAOGV)
        checkWarmFloor = findViewById(R.id.checkWarmFloor)
        checkElectro = findViewById(R.id.checkElectro)
        checkBoiler = findViewById(R.id.checkBoiler)
        radioGroupDelivery = findViewById(R.id.radioGroupDelivery)
        otherDeliveryEditText = findViewById(R.id.editOtherDelivery)
        editPrice = findViewById(R.id.editPrice)
        spinnerUtilities = findViewById(R.id.spinnerUtilities)
        spinnerChildren = findViewById(R.id.spinnerChildren)
        spinnerPets = findViewById(R.id.spinnerPets)
        checkContractMilitary = findViewById(R.id.checkContractMilitary)
        checkContractCompany = findViewById(R.id.checkContractCompany)
        editOther = findViewById(R.id.editOther)
        buttonSave = findViewById(R.id.buttonSave)

        // Используйте полученные данные по вашему усмотрению
        // Например, установите их в соответствующие текстовые поля
        contactNameEditText.setText(contactName)
        phoneNumberValueTextView.text = phoneNumber

        // Заполнение Spinner данными из ресурсов
        ArrayAdapter.createFromResource(
            this,
            R.array.layout_options,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerLayout.adapter = adapter
        }

        ArrayAdapter.createFromResource(
            this,
            R.array.layout_yn,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerUtilities.adapter = adapter
            spinnerChildren.adapter = adapter
            spinnerPets.adapter = adapter
        }

        // Добавление обработчика нажатия на кнопку "Сохранить"
        buttonSave.setOnClickListener {
            // Обработка нажатия
            saveData()
        }

        // Добавление слушателя к RadioGroup для комнат
        radioGroupRooms.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId == R.id.radioOtherRoom) {
                // Если выбрана "Другое", покажите поле для ввода
                otherRoomEditText.visibility = View.VISIBLE
            } else {
                // В противном случае скройте поле для ввода
                otherRoomEditText.visibility = View.GONE
            }
        }

        // Добавление слушателя к RadioGroup для комнат
        radioGroupDelivery.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId == R.id.radioOtherDelivery) {
                // Если выбрана "Другое", покажите поле для ввода
                otherDeliveryEditText.visibility = View.VISIBLE
            } else {
                // В противном случае скройте поле для ввода
                otherDeliveryEditText.visibility = View.GONE
            }
        }

        dbHelper = DatabaseHelper(this)

    }

    private fun saveData() {
        // Получите данные из полей ввода и других компонентов
        val contactName = contactNameEditText.text.toString()
        val phoneNumber = phoneNumberValueTextView.text.toString()
        val roomType = findViewById<RadioButton>(radioGroupRooms.checkedRadioButtonId).text.toString()
        val otherRoomDescription = if (radioGroupRooms.checkedRadioButtonId == R.id.radioOtherRoom) {
            otherRoomEditText.text.toString()
        } else {
            null
        }
        val address = addressEditText.text.toString()
        val layout = spinnerLayout.selectedItem.toString()
        val sleepingPlaces = sleepingPlacesEditText.text.toString()
        val centralHeating = checkCentralHeating.isChecked
        val aogv = checkAOGV.isChecked
        val warmFloor = checkWarmFloor.isChecked
        val electro = checkElectro.isChecked
        val boiler = checkBoiler.isChecked
        val deliveryTime = findViewById<RadioButton>(radioGroupDelivery.checkedRadioButtonId).text.toString()
        val otherDeliveryDescription = if (radioGroupDelivery.checkedRadioButtonId == R.id.radioOtherDelivery) {
            otherDeliveryEditText.text.toString()
        } else {
            null
        }
        val price = editPrice.text.toString()
        val utilities = spinnerUtilities.selectedItem.toString()
        val children = spinnerChildren.selectedItem.toString()
        val pets = spinnerPets.selectedItem.toString()
        val contractMilitary = checkContractMilitary.isChecked
        val contractCompany = checkContractCompany.isChecked
        val otherInfo = editOther.text.toString()

        // Создайте объект HouseData
        val houseData = HouseData(
            contactName,
            phoneNumber,
            roomType,
            otherRoomDescription,
            address,
            layout,
            sleepingPlaces,
            centralHeating,
            aogv,
            warmFloor,
            electro,
            boiler,
            deliveryTime,
            otherDeliveryDescription,
            price,
            utilities,
            children,
            pets,
            contractMilitary,
            contractCompany,
            otherInfo
        )

        // Сохраните данные с использованием dbHelper
        val id = dbHelper.addHouseData(houseData)

        // Оповестите пользователя о сохранении
        Toast.makeText(this, "Данные успешно сохранены с ID: $id", Toast.LENGTH_SHORT).show()

        // После сохранения переходите на карточку контакта или другую активность
        // Пример:

        // Переход на карточку контакта
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("houseDataId", id) // Передайте ID сохраненных данных в карточку контакта
        startActivity(intent)
    }

    override fun onDestroy() {
        dbHelper.close() // Важно закрыть dbHelper при уничтожении активности
        super.onDestroy()

    }
}



