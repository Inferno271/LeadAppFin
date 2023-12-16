// CallReceiver.kt
package com.inferno271.leadappfin.ui.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager

class CallReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == TelephonyManager.ACTION_PHONE_STATE_CHANGED) {
            val state = intent.getStringExtra(TelephonyManager.EXTRA_STATE)
            if (state == TelephonyManager.EXTRA_STATE_RINGING || state == TelephonyManager.EXTRA_STATE_OFFHOOK) {
                // Обработайте начало звонка
                // Вы можете отправить событие в ваш сервис
                val serviceIntent = Intent(context?.applicationContext, CallHandlingService::class.java)
                serviceIntent.action = CallHandlingService.ACTION_INCOMING_CALL
                context?.applicationContext?.startService(serviceIntent)
            } else if (state == TelephonyManager.EXTRA_STATE_IDLE) {
                // Обработайте окончание звонка
                // Вы можете отправить событие в ваш сервис
                val serviceIntent = Intent(context, CallHandlingService::class.java)
                serviceIntent.action = CallHandlingService.ACTION_CALL_ENDED
                context?.startService(serviceIntent)
            }
        }
    }
}
