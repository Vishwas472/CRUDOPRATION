package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var db: DatabaseHandler
    var datalist = ArrayList<Tasks>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DatabaseHandler(this, "database.db", null, 1)

        allData()
    }

    private fun allData() {
        binding.btnadd.setOnClickListener {
            val name = binding.txtName.text.toString()


            val Details = binding.txtDescription.text.toString()
            if(name.isEmpty())
            {
                Toast.makeText(this, "Please add Name", Toast.LENGTH_SHORT).show()
            }
            else if(Details.isEmpty()){
                Toast.makeText(this, "Please add Detalis", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this, "Category Added Sucessfully", Toast.LENGTH_SHORT).show()
            }
            db.InsertData(name,Details)

        }
        binding.btndisplay.setOnClickListener{
            datalist = db.displaydata()

            val Adpter = Adpter(datalist, onItemClick = {id,name , Detalis ->
                val i = Intent(this,UpdateActivity::class.java)
                i.putExtra("name",name)
                i.putExtra("Details",Detalis)
                i.putExtra("id",id)
                startActivity(i)
                Log.e("TAG", "allData: " +name)

            })
            var manager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            binding.rcv.layoutManager = manager
            binding.rcv.adapter = Adpter
        }




    }
}