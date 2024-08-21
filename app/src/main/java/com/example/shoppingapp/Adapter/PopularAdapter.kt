package com.example.shoppingapp.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.shoppingapp.Model.ItemsModel
import com.example.shoppingapp.activity.DetailActivity
import com.example.shoppingapp.databinding.ViewholderRecommendationBinding

class PopularAdapter(val items:MutableList<ItemsModel>):
    RecyclerView.Adapter<PopularAdapter.ViewHolder>() {

        private var context: Context?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularAdapter.ViewHolder {
     context = parent.context
        val binding = ViewholderRecommendationBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }

    class ViewHolder (val binding:ViewholderRecommendationBinding):RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: PopularAdapter.ViewHolder, position: Int) {

             holder.binding.titleTxt.text = items[position].title
             holder.binding.titleTxt.text = items[position].title
//        holder.binding.pricetxt.setText("$" + items.get(position).getPrice().toString());
        holder.binding.pricetxt.text = "$${items[position].price}"
             holder.binding.ratingTxt.text = items[position].rating.toString()

        val requestOptions = RequestOptions().transform(CenterCrop())
        Glide.with(holder.itemView.context)
            .load(items[position].picUrl[0])
            .apply(requestOptions)
            .into(holder.binding.pic)

        holder.itemView.setOnClickListener{
val intent = Intent(holder.itemView.context,DetailActivity::class.java)
            intent.putExtra("object",items[position])
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = items.size
}