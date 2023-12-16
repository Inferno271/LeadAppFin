// CallHandlingService.kt
package com.inferno271.leadappfin.ui.service

import android.Manifest
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.IBinder
import android.provider.ContactsContract
import android.telephony.TelephonyManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.inferno271.leadappfin.R
import android.provider.CallLog
import android.util.Log
import android.view.inputmethod.InputMethodManager
import com.inferno271.leadappfin.ui.form.OwnerFormActivity


class CallHandlingService : Service() {

    private var windowManager: WindowManager? = null
    private var floatingView: View? = null
    private var isFloatingWindowShown = false // Флаг для отслеживания состояния окна


    private lateinit var txtContactName: TextView
    private lateinit var txtPhoneNumber: TextView
    private lateinit var buttonClient: Button
    private lateinit var buttonOwner: Button
    private lateinit var btnCloseNote: Button

    private val callReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == "android.intent.action.PHONE_STATE") {
                val state = intent.getStringExtra(TelephonyManager.EXTRA_STATE)

                if (state == TelephonyManager.EXTRA_STATE_OFFHOOK) {
                    // Получение информации о звонящем контакте и номере
                    val phoneNumber = getPhoneNumber(context)
                    val contactName = getContactName(context, phoneNumber)

                    // Обработайте начало звонка и установите значения в TextView
                    showFloatingWindow(contactName, phoneNumber)
                } else if (state == TelephonyManager.EXTRA_STATE_IDLE) {
                    // Обработайте окончание звонка

                    // Вы можете отправить событие в ваш сервис
                    val serviceIntent = Intent(context, CallHandlingService::class.java)
                    serviceIntent.action = ACTION_CALL_ENDED
                    context?.startService(serviceIntent)
                }
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        registerCallReceiver()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterCallReceiver()
        hideFloatingWindow()
    }

    private fun registerCallReceiver() {
        val filter = IntentFilter()
        filter.addAction(TelephonyManager.ACTION_PHONE_STATE_CHANGED)
        registerReceiver(callReceiver, filter)
    }

    private fun unregisterCallReceiver() {
        unregisterReceiver(callReceiver)
    }

    private fun showFloatingWindow(contactName: String?, phoneNumber: String?) {
        if (isFloatingWindowShown) {
            return // Окно уже отображается, не создавайте его снова
        }

        // Создание всплывающего окна и его отображение
        val layoutParams = WindowManager.LayoutParams(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM,
            android.graphics.PixelFormat.TRANSLUCENT
        )
        layoutParams.gravity = Gravity.TOP or Gravity.START

        windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        floatingView = inflater.inflate(R.layout.floating_window_layout, null, false)

        // Инициализация элементов всплывающего окна
        txtContactName = floatingView!!.findViewById(R.id.txtContactName)
        txtPhoneNumber = floatingView!!.findViewById(R.id.txtPhoneNumber)
        buttonClient = floatingView!!.findViewById(R.id.buttonClient)
        buttonOwner = floatingView!!.findViewById(R.id.buttonOwner)
        btnCloseNote = floatingView!!.findViewById(R.id.btnCloseNote)

        // Установите имя контакта и номер телефона в TextView
        txtContactName.text = contactName
        txtPhoneNumber.text = phoneNumber

        // Настройка обработчика нажатия для кнопки "Клиент"
        buttonClient.setOnClickListener {



        }

        // Настройка обработчика нажатия для кнопки "Собственник"
        buttonOwner.setOnClickListener {
            // Создайте Intent для открытия OwnerFormActivity
            val intent = Intent(this, OwnerFormActivity::class.java)

            // Добавьте данные в Intent
            intent.putExtra("contactName", contactName)
            intent.putExtra("phoneNumber", phoneNumber)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            // Запустите OwnerFormActivity
            startActivity(intent)
            hideFloatingWindow()
        }


        // Настройка обработчика нажатия для кнопки "Закрыть"
        btnCloseNote.setOnClickListener {

            // Закройте всплывающее окно
            hideFloatingWindow()
        }

        windowManager?.addView(floatingView, layoutParams)
        Log.d("PopupDebug", "Окно создано")
        isFloatingWindowShown = true // Устанавливаем флаг, что окно создано
    }



    private fun hideFloatingWindow() {
        // Закрытие всплывающего окна
        if (windowManager != null && floatingView != null) {
            windowManager?.removeView(floatingView)
            floatingView = null
            isFloatingWindowShown = false // Сбрасываем флаг
        }
    }



    private fun getPhoneNumber(context: Context?): String {
        if (ContextCompat.checkSelfPermission(
                context!!,
                Manifest.permission.READ_CALL_LOG
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            val projection = arrayOf(CallLog.Calls.NUMBER)
            val sortOrder = "${CallLog.Calls.DATE} DESC"

            val cursor = context.contentResolver.query(
                CallLog.Calls.CONTENT_URI,
                projection,
                null,
                null,
                sortOrder
            )

            cursor?.use {
                if (it.moveToFirst()) {
                    val numberColumnIndex = it.getColumnIndex(CallLog.Calls.NUMBER)
                    if (numberColumnIndex != -1) {
                        val number = it.getString(numberColumnIndex)
                        if (!number.isNullOrBlank()) {
                            return number
                        }
                    }
                }
            }
        }
        return "Unknown"
    }







    private fun getContactName(context: Context?, phoneNumber: String?): String {
        if (phoneNumber == null || context == null) {
            return "Unknown"
        }

        // Проверка разрешения
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_CONTACTS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Если разрешение не предоставлено, верните "Unknown" или запросите разрешение у пользователя
            return "Unknown"
        }

        val uri = Uri.withAppendedPath(
            ContactsContract.PhoneLookup.CONTENT_FILTER_URI,
            Uri.encode(phoneNumber)
        )

        val projection = arrayOf(ContactsContract.PhoneLookup.DISPLAY_NAME)
        val cursor: Cursor? = context.contentResolver.query(uri, projection, null, null, null)

        cursor?.use {
            if (it.moveToFirst()) {
                val contactNameColumnIndex = it.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME)
                return it.getString(contactNameColumnIndex)
            }
        }

        return "Unknown"
    }



    companion object {
        const val ACTION_INCOMING_CALL = "com.inferno271.leadappfin.INCOMING_CALL"
        const val ACTION_CALL_ENDED = "com.inferno271.leadappfin.CALL_ENDED"
    }
}
