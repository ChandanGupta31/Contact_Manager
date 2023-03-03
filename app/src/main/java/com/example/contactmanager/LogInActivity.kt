package com.example.contactmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.contactmanager.databinding.ActivityLogInBinding
import com.google.firebase.database.FirebaseDatabase

class LogInActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLogInBinding
    private val dbref = FirebaseDatabase.getInstance().getReference("user")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.logInL.setOnClickListener {
            dbref.child(binding.uniqueL.text.toString()).get().addOnSuccessListener {
                if(it.exists()){
                    val value = it.child("password").value.toString()
                    if(value == binding.passwordL.text.toString()){
                        Toast.makeText(this, "Welcome!!", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, HomeActivity::class.java)
                        intent.putExtra("Current", binding.uniqueL.text.toString())
                        startActivity(intent)
                        finishAffinity()
                    }
                    else{
                        Toast.makeText(this,"Password Incorrect", Toast.LENGTH_SHORT).show()
                    }
                }
                else{
                    Toast.makeText(this,"User Not Exist", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}