package com.example.practice1.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class sqlitehelper(context: Context):SQLiteOpenHelper(context, dbname,null, dbversion) {
    companion object{
        private val dbname = "dbregister"
        private val tblname = "register"
        private val dbversion = 1
        private val column1 = "Username"
        private val column2 = "Password"
        private val column3 = "EmailId"
        private val column4 = "Phonenumber"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        val query = "Create table $tblname($column1 Text Primary Key,$column2 Integer,$column3 Text,$column4 Integer)"
        p0?.execSQL(query)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        val query = "Drop table $tblname"
        p0?.execSQL(query)
        onCreate(p0)
    }

    fun addregister(registerlist : register):Boolean
    {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(column1,registerlist.Username)
        values.put(column2,registerlist.Password)
        values.put(column3,registerlist.EmailId)
        values.put(column4,registerlist.Phonenumber)

        var result = db.insert(tblname,null,values)
        if(Integer.parseInt(result.toString())==-1)
        {
            return false
        }
        else {
            return true
        }
    }
}