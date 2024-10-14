package com.example.simplebankingapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
class MainActivity : AppCompatActivity() {
    // Initial balance
    private var balance: Double = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // UI elements
        val tvBalance: TextView = findViewById(R.id.tvBalance)
        val etDepositAmount: EditText = findViewById(R.id.etDepositAmount)
        val etWithdrawAmount: EditText = findViewById(R.id.etWithdrawAmount)
        val btnDeposit: Button = findViewById(R.id.btnDeposit)
        val btnWithdraw: Button = findViewById(R.id.btnWithdraw)
        // Display initial balance
        tvBalance.text = "Balance: $$balance"
        // Handle Deposit Button
        btnDeposit.setOnClickListener {
            val depositAmount = etDepositAmount.text.toString().toDoubleOrNull()
            if (depositAmount != null && depositAmount > 0) {
                balance += depositAmount
                tvBalance.text = "Balance: $$balance"
                Toast.makeText(this, "Deposited $$depositAmount", Toast.LENGTH_SHORT).show()
                etDepositAmount.text.clear()
            } else {
                Toast.makeText(this, "Enter a valid amount", Toast.LENGTH_SHORT).show()
            }
        }
        // Handle Withdraw Button
        btnWithdraw.setOnClickListener {
            val withdrawAmount = etWithdrawAmount.text.toString().toDoubleOrNull()
            if (withdrawAmount != null && withdrawAmount > 0 && withdrawAmount <= balance) {
                balance -= withdrawAmount
                tvBalance.text = "Balance: $$balance"
                Toast.makeText(this, "Withdrew $$withdrawAmount", Toast.LENGTH_SHORT).show()
                etWithdrawAmount.text.clear()
            } else if (withdrawAmount != null && withdrawAmount > balance) {
                Toast.makeText(this, "Insufficient balance", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Enter a valid amount", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
