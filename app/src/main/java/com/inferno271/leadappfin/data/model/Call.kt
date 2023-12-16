package com.inferno271.leadappfin.data.model

data class Call(
    val id: Long,
    val contactName: String?,
    val phoneNumber: String,
    val callDate: String,
    val noteTitle: String?,
    val noteText: String?,
    val noteDate: String
)