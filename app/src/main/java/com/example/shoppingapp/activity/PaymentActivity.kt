package com.example.shoppingapp.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.shoppingapp.R

class PaymentActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var upiInputLayout: LinearLayout
    private lateinit var upiInputPlaceholder: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        upiInputPlaceholder = findViewById(R.id.upiInputPlaceholder)
        val inflater = LayoutInflater.from(this)
        upiInputLayout = inflater.inflate(R.layout.upi_input, upiInputPlaceholder, false) as LinearLayout
        upiInputPlaceholder.addView(upiInputLayout)
        upiInputLayout.visibility = View.GONE

        val gpay = findViewById<RelativeLayout>(R.id.gpayLayout)
        val phonepe = findViewById<RelativeLayout>(R.id.PhonepeLayout)
        val paytm = findViewById<RelativeLayout>(R.id.PaytmLayout)

        gpay.setOnClickListener(this)
        phonepe.setOnClickListener(this)
        paytm.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.gpayLayout, R.id.PhonepeLayout, R.id.PaytmLayout -> {
                showOrHideUpiInput(true)
            }
        }
    }

    private fun showOrHideUpiInput(show: Boolean) {
        upiInputLayout.visibility = if (show) View.VISIBLE else View.GONE
    }
}
