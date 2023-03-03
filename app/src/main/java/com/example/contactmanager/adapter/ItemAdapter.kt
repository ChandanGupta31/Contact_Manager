package com.example.contactmanager.adapter

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.contactmanager.R
import com.example.contactmanager.model.Contacts

class ItemAdapter (private val context: Context,private val list : ArrayList<Contacts>) : RecyclerView.Adapter<ItemAdapter.ItemAdapterViewHolder>(){

    class ItemAdapterViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val name : TextView = itemView.findViewById(R.id.contact_item_member)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapterViewHolder {
        val inflator = LayoutInflater.from(parent.context).inflate(R.layout.contact_item,parent,false)
        return ItemAdapterViewHolder(inflator)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ItemAdapterViewHolder, position: Int) {
        val data = list[position]
        holder.name.text = data.name
        holder.name.setOnClickListener {
            val viewDetailDialog = Dialog(context)
            viewDetailDialog.setContentView(R.layout.show_details)
            val ok : Button = viewDetailDialog.findViewById(R.id.ok)
            val nameD : TextView = viewDetailDialog.findViewById(R.id.nameD)
            val emailD : TextView = viewDetailDialog.findViewById(R.id.emailD)
            val contactD : TextView = viewDetailDialog.findViewById(R.id.contactD)

            nameD.text = data.name
            emailD.text = data.email
            contactD.text = data.contact
            ok.setOnClickListener { viewDetailDialog.dismiss() }

            viewDetailDialog.show()
        }
    }

}