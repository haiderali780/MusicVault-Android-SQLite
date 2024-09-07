package com.example.practice1.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class loginsqlitehelper(context: Context) : SQLiteOpenHelper(context, dbname,null, dbversion) {
    companion object{
        private val dbname = "dblogin"
        private val tblname = "login"
        private val dbversion = 1
        private val column1 = "EmailId"
        private val column2 = "Password"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        val query = "Create table $tblname($column1 Text Primary Key,$column2 Integer)"
        p0?.execSQL(query)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        val query = "Drop table $tblname"
        p0?.execSQL(query)
        onCreate(p0)
    }

    fun addlogin(loginlist : login):Boolean
    {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(column1,loginlist.EmailId)
        values.put(column2,loginlist.Password)

        val result = db.insert(tblname,null,values)
        if (Integer.parseInt(result.toString())==-1)
        {
            return false
        }
        else{
            return true
        }
    }
}