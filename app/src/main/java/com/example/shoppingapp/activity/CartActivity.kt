package com.example.shoppingapp.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project1762.Helper.ChangeNumberItemsListener
import com.example.shoppingapp.Adapter.CartAdapter
import com.example.shoppingapp.Helper.ManagmentCart
import com.example.shoppingapp.databinding.ActivityCartBinding

class CartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCartBinding
    private lateinit var managmentCart: ManagmentCart


    private var tax: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managmentCart = ManagmentCart(this)
        setVariable()
        initcartlist()
        calculateCart()

        binding.checkout.setOnClickListener{
            startActivity(Intent(this@CartActivity, PaymentActivity::class.java))
        }
    }

    private fun initcartlist() {
        binding.viewCart.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        binding.viewCart.adapter = CartAdapter(managmentCart.getListCart(), this, object : ChangeNumberItemsListener {
            override fun onChanged() {
                calculateCart()  // Update the total fees when the cart changes
            }
        })

        

        with(binding) {
            emptyTxt.visibility = if (managmentCart.getListCart().isEmpty()) View.VISIBLE else View.GONE
            scrollview.visibility = if (managmentCart.getListCart().isEmpty()) View.GONE else View.VISIBLE
        }
    }

    private fun calculateCart() {
        val percentTax = 0.02
        val delivery = 10.0
        val totalFee = managmentCart.getTotalFee()

        tax = Math.round((totalFee * percentTax) * 100) / 100.0
        val total = Math.round((totalFee + tax + delivery) * 100) / 100.0
        val itemTotal = Math.round(totalFee * 100) / 100.0

        with(binding) {
            totalfeeTxt.text = "$$itemTotal"
            taxtxt.text = "$$tax"
            Deliverytxt.text = "$$delivery"
            totalTxt.text = "$$total"
        }
    }

    private fun setVariable() {
        binding.backbtn.setOnClickListener { finish() }
    }
}
