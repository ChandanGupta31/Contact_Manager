package com.example.contactmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.contactmanager.databinding.ActivitySignUpBinding
import com.example.contactmanager.model.userDetails
import com.google.firebase.database.FirebaseDatabase


class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private val dbref  = FirebaseDatabase.getInstance().getReference("user")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.signUp.setOnClickListener {
            if(checkID() && checkDetails()){
                val user = userDetails(binding.nameS.text.toString(),
                    binding.emailS.text.toString(),
                    binding.uniqueS.text.toString(),
                    binding.passwordS.text.toString())
                dbref.child(binding.uniqueS.text.toString()).setValue(user)
                Toast.makeText(this, "Welcome!!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra("Current", binding.uniqueS.text.toString())
                startActivity(intent)
                finishAffinity()
            }
        }

        binding.logInS.setOnClickListener {
            startActivity(Intent(this, LogInActivity::class.java))
        }

    }

    private fun checkDetails() : Boolean{
        if(binding.nameS.text!!.isEmpty() || binding.emailS.text!!.isEmpty() || binding.uniqueS.text!!.isEmpty() || binding.passwordS.text!!.isEmpty()){
            Toast.makeText(this, "Please Fill All Details", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun checkID() : Boolean{
        var value = true
        dbref.child(binding.uniqueS.text.toString()).get().addOnSuccessListener {
            if (it.exists()){
                Toast.makeText(this, "User Already Exist", Toast.LENGTH_LONG).show()
                value = false
            }
        }
        return value
    }
}