package com.example.lesson2.features.productlist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.lesson2.databinding.ActivityMainBinding
import com.example.lesson2.features.newproduct.AddProductActivity
import com.example.lesson2.model.Product


class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    lateinit var viewModel : MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel=ViewModelProvider(this).get(MainActivityViewModel::class.java)

        binding=ActivityMainBinding.inflate(layoutInflater)
        binding.lifecycleOwner=this
        setContentView(binding.root)

        binding.viewModel=viewModel

    }

    val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            var product = result.data?.getParcelableExtra<Product>("product")
            println(product)
        }
    }
    fun observeAll(){
        viewModel.buttonClickObserver.observe(this){
            if(it){
                val intent=Intent(this, AddProductActivity::class.java)
                startForResult.launch(intent)
            }
        }
    }

    fun removeAllObserves(){
        viewModel.buttonClickObserver.removeObservers(this)
        viewModel.buttonClickObserver.postValue(false)
    }

    override fun onResume() {
        super.onResume()
        observeAll()
    }

    override fun onPause() {
        super.onPause()
        removeAllObserves()
    }
}