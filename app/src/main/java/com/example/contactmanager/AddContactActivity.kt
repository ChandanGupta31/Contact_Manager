package com.example.contactmanager

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.contactmanager.databinding.ActivityAddContactBinding
import com.google.firebase.database.FirebaseDatabase

class AddContactActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddContactBinding
    private val dbref = FirebaseDatabase.getInstance().getReference("user")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getStringExtra("Current").toString()

        binding.addNewContact.setOnClickListener {
            val newContact = com.example.contactmanager.model.Contacts(
                binding.nameNew.text.toString(),
                binding.emailNew.text.toString(),
                binding.contactNew.text.toString()
            )

            val alertDial = Dialog(this)
            alertDial.setContentView(R.layout.save_dialog)
            val ok = alertDial.findViewById<Button>(R.id.ok)

            ok.setOnClickListener {
                binding.nameNew.text = null
                binding.emailNew.text = null
                binding.contactNew.text = null
                alertDial.dismiss()
            }

            dbref.child(data).child(data+"contacts").push().setValue(newContact).addOnSuccessListener {
                alertDial.show()
            }
        }

    }
}