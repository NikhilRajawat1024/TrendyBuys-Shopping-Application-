package com.example.shoppingapp.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.shoppingapp.Adapter.BrandAdapter
import com.example.shoppingapp.Adapter.PopularAdapter
import com.example.shoppingapp.Model.SliderModel
import com.example.shoppingapp.R
import com.example.shoppingapp.Adapter.SliderAdapter
import com.example.shoppingapp.ViewModel.MainViewModel
import com.example.shoppingapp.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBanner()
        initBrand()
        initPopular()
        initBottomMenu()
    }

    private fun initBottomMenu() {
        binding.cartbtn.setOnClickListener{
            startActivity(
                Intent(
                    this@MainActivity,
                    CartActivity::class.java
                )
            )
        }
    }

    private fun initBanner() {
        binding.progressBarBrand.visibility = View.VISIBLE
        viewModel.banners.observe(this) { items ->
            if (items.isNullOrEmpty()) {
                // Handle the empty or null case
                binding.progressBarBrand.visibility = View.GONE
                // Optionally,show an error message or placeholder
            } else {
                banners(items)
                binding.progressBarBrand.visibility = View.GONE
            }
        }
        viewModel.loadBanner()
    }

    private fun banners(images:List<SliderModel>) {
        binding.viewpageslider.adapter = SliderAdapter(images, binding.viewpageslider)
        binding.viewpageslider.clipToPadding = false
        binding.viewpageslider.clipChildren = false
        binding.viewpageslider.offscreenPageLimit = 1
        binding.viewpageslider.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer = CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(40))
        }
        binding.viewpageslider.setPageTransformer(compositePageTransformer)


        val pageMarginPx = resources.getDimensionPixelOffset(R.dimen.pageMargin)
        val offsetPx = resources.getDimensionPixelOffset(R.dimen.offset)
        binding.viewpageslider.setPageTransformer(MarginPageTransformer(pageMarginPx))

        if (images.size > 1) {
            binding.dotindicator.visibility = View.VISIBLE
            binding.dotindicator.attachTo(binding.viewpageslider)
        } else {
            binding.dotindicator.visibility = View.GONE
        }
    }

    private fun initBrand(){
        binding.progressBarBrand.visibility = View.VISIBLE
        viewModel.brands.observe(this, Observer {
            binding.viewbrand.layoutManager =
                LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
            binding.viewbrand.adapter = BrandAdapter(it)
            binding.progressBarBrand.visibility = View.GONE
        })

        viewModel.loadBrand()
    }

    private fun initPopular(){
        binding.progressBarPopular.visibility = View.VISIBLE
        viewModel.popular.observe(this, Observer {
            binding.viewPopular.layoutManager = GridLayoutManager(this@MainActivity,2)
            binding.viewPopular.adapter = PopularAdapter(it)
            binding.progressBarPopular.visibility = View.GONE
        })

        viewModel.loadPopular()
    }
}
