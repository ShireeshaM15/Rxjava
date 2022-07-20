package com.example.rxjava

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rxjava.databinding.LayoutRvItemBinding


class MainAdapter : RecyclerView.Adapter<MainViewHolder>() {

    var brand = mutableListOf<ProductModel>()

    fun productlist(m: List<ProductModel>) {
        this.brand = m.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = LayoutRvItemBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val maybeline = brand[position]
        holder.binding.productname.text = maybeline.name
        holder.binding.productprice.text = maybeline.price

    }

    override fun getItemCount(): Int {
        return brand.size
    }
}

class MainViewHolder(val binding: LayoutRvItemBinding) : RecyclerView.ViewHolder(binding.root) {}
