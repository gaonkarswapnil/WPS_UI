package com.example.wpsui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Payroll (
    val paymentMonth: String,
    val paymentId: String,
    val status: Status,
    val noOfSalaries: Int,
    val creationDate: String,
    val paymentDate: String,
    val amount: String
): Parcelable