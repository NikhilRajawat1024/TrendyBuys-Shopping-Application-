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

class BrandAdapter(private val items: MutableList<BrandModel>) :
    RecyclerView.Adapter<BrandAdapter.ViewHolder>() {

    private var selectedPosition = -1
    private var lastSelectedPosition = -1
    private lateinit var context: Context

    class ViewHolder(val binding: ViewholderBrandBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val binding = ViewholderBrandBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    @SuppressLint("RecyclerView")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.binding.title.text = item.title

        // Load image using Glide
        Glide.with(holder.itemView.context)
            .load(item.picUrl)
            .into(holder.binding.pic)

        holder.binding.root.setOnClickListener {
            lastSelectedPosition = selectedPosition
            selectedPosition = position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)
        }

        holder.binding.title.setTextColor(context.resources.getColor(R.color.white))

        // Update UI based on the selection state
        if (selectedPosition == position) {
            holder.binding.pic.setBackgroundResource(0)
            holder.binding.mainLayout.setBackgroundResource(R.drawable.red_bg)
            ImageViewCompat.setImageTintList(holder.binding.pic, ColorStateList.valueOf(context.getColor(R.color.white)))
            holder.binding.title.visibility = View.VISIBLE
            holder.binding.title.setTextColor(context.getColor(R.color.white))
        } else {
            holder.binding.pic.setBackgroundResource(R.drawable.grey_bg)
            holder.binding.mainLayout.setBackgroundResource(0)
            ImageViewCompat.setImageTintList(holder.binding.pic, ColorStateList.valueOf(context.getColor(R.color.black)))
            holder.binding.title.visibility = View.GONE
        }

        Log.d("BrandAdapter", "Loading image for URL: ${item.picUrl}")
    }

    override fun getItemCount(): Int = items.size
}
