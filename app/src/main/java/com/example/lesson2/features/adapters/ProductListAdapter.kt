package com.example.lesson2.features.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson2.R
import com.example.lesson2.databinding.ListviewItemProductBinding
import com.example.lesson2.model.Product
import java.util.ListResourceBundle

class ProductListAdapter(val context: Context, private  var datalist: MutableList<Product>, var onClick: (Product)->Unit) : BaseAdapter() {

    fun addNewItem(product:Product){
        datalist.add(product)
        notifyDataSetChanged()
    }
    override fun getCount(): Int {
        return datalist.count()
    }

    override fun getItem(position: Int): Any {
        TODO("Not yet implemented")
        return datalist.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, contentView: View?, p2: ViewGroup?): View {

        var newContentView=contentView;
        var holder : ViewHolder
        if(contentView == null){
            var binding : ListviewItemProductBinding = DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.listview_item_product, p2, false)

            newContentView=binding.root
            holder=ViewHolder(binding, onClick)
            holder.bind(datalist[position])
            newContentView?.tag=holder

            return binding.root

        }
        else{
            holder=contentView.tag as ViewHolder
            holder.bind(datalist[position])
        }

        return newContentView!!


    }

    private class ViewHolder(var binding : ListviewItemProductBinding, var onClick: (Product)->Unit){

        fun bind(product: Product){
            binding.txtProductName.text=product.name
            binding.txtProductDetaile.text=product.description
            binding.product=product
            binding.root.setOnClickListener{
                onClick(binding.product as Product)
            }
        }

    }
}