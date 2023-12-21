package com.example.lesson2.features.newproduct

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lesson2.model.Product

class AddProductViewModel : ViewModel() {
    var productName = MutableLiveData<String>()
    var productCode = MutableLiveData<String>()
    var productDescription = MutableLiveData<String>()
    var productList = mutableListOf<Product>()

    var addButtonClickObserver = MutableLiveData<Boolean>()

    var errorObserver = MutableLiveData<String>()

    fun addProduct() {

        if(productName.value.isNullOrEmpty() || productCode.value.isNullOrEmpty() || productDescription.value.isNullOrEmpty()){
            errorObserver.postValue("Eksik bilgiler!")
            return
        }
        addButtonClickObserver.postValue(true)
    }

    fun createNewProduct() : Product{
        return Product(productName.value ?: "", productCode.value ?: "", productDescription.value ?: "")
    }
}