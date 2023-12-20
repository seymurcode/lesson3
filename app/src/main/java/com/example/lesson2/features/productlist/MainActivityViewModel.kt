package com.example.lesson2.features.productlist
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    var buttonClickObserver=MutableLiveData<Boolean>()

    fun buttonClick(){
        buttonClickObserver.postValue(true)
    }

}