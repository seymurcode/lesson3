package com.example.lesson2.features.newproduct

import android.app.Activity
import android.content.Intent
import android.health.connect.datatypes.units.Length
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.lesson2.R
import com.example.lesson2.databinding.ActivityAddProductBinding
import com.example.lesson2.databinding.ActivityMainBinding
import com.example.lesson2.features.productlist.MainActivityViewModel
import com.example.lesson2.model.Product

class AddProductActivity : AppCompatActivity() {

    lateinit var binding : ActivityAddProductBinding
    lateinit var viewModel : AddProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel= ViewModelProvider(this).get(AddProductViewModel::class.java)

        binding=ActivityAddProductBinding.inflate(layoutInflater)
        binding.lifecycleOwner=this
        setContentView(binding.root)

        binding.viewModel=viewModel



    }

    fun observeAll(){
        viewModel.addButtonClickObserver.observe(this){
            if(it){
                val product : Product? =viewModel.createNewProduct()

                val resultIntent = Intent()
                resultIntent.putExtra("product", product)
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            }
        }

        viewModel.errorObserver.observe(this){
            if(!it.isNullOrEmpty()){
                Toast.makeText(this,it, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun removeAllObservers(){
        viewModel.addButtonClickObserver.removeObservers(this)
        viewModel.addButtonClickObserver.postValue(false)

        viewModel.errorObserver.removeObservers(this)
        //viewModel.errorObserver.postValue(false)
    }

    override fun onResume() {
        super.onResume()
        observeAll()
    }

    override fun onPause() {
        super.onPause()
        removeAllObservers()
    }
}