package com.example.myapplication

import android.content.Intent
import android.telecom.Call.Details
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adpter(var datalist: ArrayList<Tasks>, var onItemClick: ((id:String,name: String, Details : String) -> Unit)) :
    RecyclerView.Adapter<Adpter.Myviewholder>() {
    class Myviewholder(v: View) : RecyclerView.ViewHolder(v) {

        var txtcategory: TextView = v.findViewById(R.id.tvName)
        var txtdetails: TextView = v.findViewById(R.id.tvDesc)
        var edit: ImageView = v.findViewById(R.id.edit)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Myviewholder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_file, parent, false)
        val adapter = Myviewholder(v)
        return adapter
    }

    override fun getItemCount(): Int {
        return datalist.size
    }

    override fun onBindViewHolder(holder: Myviewholder, position: Int) {
        holder.txtcategory.setText(datalist[position].name)
        holder.txtdetails.setText(datalist[position].Details)

        holder.edit.setOnClickListener {

            Log.e("TAG", "select : "+datalist[position].id)
            onItemClick.invoke(
                datalist[position].id,datalist[position].name,
                              datalist[position].Details)
        }


    }

}


//    fun refreshdata(newdata : RecyclerView<Tasks>){
//        datalist = newdata
//        notifyDataSetChanged()
//    }
