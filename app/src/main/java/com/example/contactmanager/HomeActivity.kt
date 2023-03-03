package com.example.contactmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.contactmanager.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getStringExtra("Current")

        binding.addContact.setOnClickListener {
            val intent = Intent(this, AddContactActivity::class.java)
            intent.putExtra("Current",data)
            startActivity(intent)
        }

        binding.viewContacts.setOnClickListener {
            val intent = Intent(this, ViewContactActivity::class.java)
            intent.putExtra("Current",data)
            startActivity(intent)
        }
    }
}