package com.isa.loancalculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import kotlin.math.pow

class MainActivity : AppCompatActivity()
{
    private var etLoanAmount: EditText? = null
    private var etLoanInterest: EditText? = null
    private var etMonths: EditText? = null
    private var tvMonthlyPayment: TextView? = null
    private var btnCalculate: Button? = null
    private var btnReset: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        etLoanAmount = findViewById<EditText>(R.id.et_loan_amount)
        etLoanInterest = findViewById<EditText>(R.id.et_loan_interest)
        etMonths = findViewById<EditText>(R.id.et_months)
        tvMonthlyPayment = findViewById<TextView>(R.id.tv_monthly_payment)
        val btnCalculate = findViewById<Button>(R.id.btn_calculate)
        val btnReset = findViewById<Button>(R.id.btn_reset)

        btnCalculate.setOnClickListener(View.OnClickListener { calculateMonthlyPayment() })

        btnReset.setOnClickListener(View.OnClickListener { resetFields() })
    }

    private fun calculateMonthlyPayment() {
        val loanAmountStr = etLoanAmount!!.text.toString()
        val loanInterestStr = etLoanInterest!!.text.toString()
        val monthsStr = etMonths!!.text.toString()

        if (!loanAmountStr.isEmpty() && !loanInterestStr.isEmpty() && !monthsStr.isEmpty()) {
            val loanAmount = loanAmountStr.toDouble()
            val annualInterestRate = loanInterestStr.toDouble()
            val months = monthsStr.toInt()

            val monthlyInterestRate = annualInterestRate / 12 / 100
            val monthlyPayment: Double =
                (loanAmount * monthlyInterestRate) / (1 - (1 + monthlyInterestRate).pow(-months.toDouble()))

            tvMonthlyPayment!!.text = String.format("Monthly Payment: %.2f", monthlyPayment)
        } else {
            tvMonthlyPayment!!.text = "Please fill all fields."
        }
    }

    private fun resetFields() {
        etLoanAmount!!.setText("")
        etLoanInterest!!.setText("")
        etMonths!!.setText("")
        tvMonthlyPayment!!.text = "Monthly Payment: "
    }
}
