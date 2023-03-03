package com.example.contactmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactmanager.adapter.ItemAdapter
import com.example.contactmanager.databinding.ActivityViewContactBinding
import com.example.contactmanager.model.Contacts
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ViewContactActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewContactBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rcv.layoutManager = LinearLayoutManager(this)
        binding.rcv.setHasFixedSize(true)
        val user = intent.getStringExtra("Current").toString()
        val ref = FirebaseDatabase.getInstance().getReference("user").child(user).child(user+"contacts")

        val contactList = arrayListOf<Contacts>()
        ref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                contactList.clear()
                if(snapshot.exists()){
                    for(member in snapshot.children){
                        val memberData = member.getValue(Contacts::class.java)
                        contactList.add(memberData!!)
                    }
                    binding.rcv.adapter = ItemAdapter(this@ViewContactActivity, contactList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

    }
}