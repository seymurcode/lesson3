package com.example.lesson2.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(val name : String, val code : String, val description : String) : Parcelable {

}