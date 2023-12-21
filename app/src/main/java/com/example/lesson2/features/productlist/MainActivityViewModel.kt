package com.example.lesson2.features.productlist
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lesson2.model.Product

class MainActivityViewModel : ViewModel() {


    var productList = mutableListOf<Product>()
    var buttonClickObserver=MutableLiveData<Boolean>()

    fun buttonClick(){
        buttonClickObserver.postValue(true)
    }

    fun addProduct(product:Product) : Product?{
        if(product.name.isEmpty() || product.code.isEmpty()) {
            return null
        }

        return product;
    }

}