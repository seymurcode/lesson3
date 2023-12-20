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

    var errorObserver = MutableLiveData<Boolean>()

    fun addProduct() : Product? {
        addButtonClickObserver.postValue(true)

        if(productName.value.isNullOrEmpty() && productCode.value.isNullOrEmpty() && productDescription.value.isNullOrEmpty()){
            errorObserver.postValue(true)
            return null
        }

        return Product(productName.value.orEmpty(), productCode.value.orEmpty(), productDescription.value.orEmpty())
    }
}