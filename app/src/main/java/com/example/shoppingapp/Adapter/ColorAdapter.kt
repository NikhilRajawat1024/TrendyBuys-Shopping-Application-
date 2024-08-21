package com.example.shoppingapp.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shoppingapp.Model.BrandModel
import com.example.shoppingapp.R
import com.example.shoppingapp.databinding.ViewholderBrandBinding
import com.example.shoppingapp.databinding.ViewholderColorBinding

class ColorAdapter(private val items: MutableList<String>) :
    RecyclerView.Adapter<ColorAdapter.ViewHolder>() {

    private var selectedPosition = -1
    private var lastSelectedPosition = -1
    private lateinit var context: Context

  inner class ViewHolder(val binding: ViewholderColorBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val binding = ViewholderColorBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    @SuppressLint("RecyclerView")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        // Load image using Glide
        Glide.with(holder.itemView.context)
            .load(items[position])
            .into(holder.binding.pic)

        holder.binding.root.setOnClickListener {
            lastSelectedPosition = selectedPosition
            selectedPosition = position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)
        }



        // Update UI based on the selection state
        if (selectedPosition == position) {
            holder.binding.colorLayout.setBackgroundResource(R.drawable.grey_bg_selector)

        } else {
            holder.binding.colorLayout.setBackgroundResource(R.drawable.grey_bg)

        }

//        Log.d("BrandAdapter", "Loading image for URL: ${items.picUrl}")
    }

    override fun getItemCount(): Int = items.size
}
