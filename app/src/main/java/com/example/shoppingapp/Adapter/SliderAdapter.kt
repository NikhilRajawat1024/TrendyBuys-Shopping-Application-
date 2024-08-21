package com.example.shoppingapp.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.request.RequestOptions
import com.example.shoppingapp.Model.SliderModel
import com.example.shoppingapp.R

class SliderAdapter(
    private var sliderItems: List<SliderModel>,
    private val viewPager2: ViewPager2
) : RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SliderViewHolder {
        context = parent.context
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.slider_item_container, parent, false)
        return SliderViewHolder(view)
    }

    override fun getItemCount(): Int = sliderItems.size

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        holder.setImage(sliderItems[position], context)
        if (position == sliderItems.lastIndex) {
            viewPager2.post(runnable)
        }
    }

    private val runnable = Runnable {
        sliderItems = sliderItems
        notifyDataSetChanged()
    }

    class SliderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.imageslider)
        @SuppressLint("CheckResult")
        fun setImage(sliderItem: SliderModel, context: Context) {
            val requestOption = RequestOptions().transform(CenterInside())

            Glide.with(context)
                .load(sliderItem.url)
                .apply(requestOption)
                .into(imageView)
        }
    }
}
