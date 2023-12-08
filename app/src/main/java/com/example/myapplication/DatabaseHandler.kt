package com.example.myapplication

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHandler(context: Context, s: String, nothing: Nothing?, i: Int) : SQLiteOpenHelper(context, "database.db", null, 1) {
    var datalist = ArrayList<Tasks>()


    override fun onCreate(p0: SQLiteDatabase?) {
        var table2 =
            "Create table studenttb(id integer primary key Autoincrement,name text,Details text)"
        p0?.execSQL(table2)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }

    fun InsertData(name: String, Details: String) {

        val db = writableDatabase
        var c = ContentValues()
        c.put("name", name)
        c.put("Details", Details)
        db.insert("studenttb", null, c)
    }

    fun displaydata(): ArrayList<Tasks> {
        datalist.clear()

        val db = readableDatabase
        val sql = "select * from studenttb"
        var cursor = db.rawQuery(sql, null)
        if (cursor.count > 0) {
            cursor.moveToFirst()
            do {

                val id = cursor.getString(0)
                val name = cursor.getString(1)
                val Details = cursor.getString(2)

                var categorylist = Tasks(id, name, Details)
                datalist.add(categorylist)
            } while (cursor.moveToNext())


            Log.e("TAG", "display:size " + datalist.size)
        } else {
            Log.e("TAG", "display: " + "no data found")
        }
        return datalist
    }


    fun updateData(id: String, name: String, Details: String){
        var db = this.writableDatabase
        var c = ContentValues()
        c.put("name" ,name)
        c.put("id" ,id)
        c.put("Details" ,Details)
        db.update("studenttb",c,"id="+id, arrayOf(id.toString()))

        db.close()


    }




}