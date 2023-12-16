// CallService.kt
package com.inferno271.leadappfin.ui.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.telecom.Call
import android.telecom.InCallService
//import android.telecom.InCallService.CallNotification
import android.telecom.TelecomManager
import android.telecom.VideoProfile
import android.util.Log
import androidx.core.app.NotificationCompat

class CallService : InCallService() {

    override fun onCallAdded(call: Call) {
        super.onCallAdded(call)

        // Отображение всплывающего окна для нового звонка
        showCallNotification(call)
    }

    override fun onCallRemoved(call: Call) {
        super.onCallRemoved(call)

        // Закрытие всплывающего окна при завершении звонка
        closeCallNotification(call)
    }

    private fun showCallNotification(call: Call) {
        // Реализуйте логику отображения уведомления для звонка
    }

    private fun closeCallNotification(call: Call) {
        // Реализуйте логику закрытия уведомления при завершении звонка
    }
}
