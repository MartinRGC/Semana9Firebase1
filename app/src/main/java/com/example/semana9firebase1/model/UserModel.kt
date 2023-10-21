package com.example.semana9firebase1.model

import android.provider.ContactsContract.CommonDataKinds.Email

data class UserModel(
    val email: String="",
    val password: String="",
    val fullname: String="",
    val country: String="",
    val UID: String=""


)