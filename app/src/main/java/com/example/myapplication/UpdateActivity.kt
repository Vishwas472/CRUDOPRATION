package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.databinding.ActivityUpdateBinding

class UpdateActivity : AppCompatActivity() {
    lateinit var binding: ActivityUpdateBinding
    lateinit var db: DatabaseHandler
    var id_number = 0
    lateinit var id:String



    var datalist = ArrayList<Tasks>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DatabaseHandler(this, "database.db", null, 1,)
        allData()
    }

    private fun allData() {




        if(intent != null)
        {
            val name: String? = intent.getStringExtra("name")
            val Details: String? = intent.getStringExtra("Details")
             id = intent.getStringExtra("id").toString()


            binding.txtName.setText(name)
            binding.txtDescription.setText(Details)


        }
//
//




        binding.btnSave.setOnClickListener{
            val name = binding.txtName.text.toString()
            val details = binding.txtDescription.text.toString()
            

            db.updateData(id,name,details)
            finish()


            Toast.makeText(this, "changes saved", Toast.LENGTH_SHORT).show()

        }
    }
}