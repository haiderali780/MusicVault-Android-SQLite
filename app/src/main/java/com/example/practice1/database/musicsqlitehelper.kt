package com.example.practice1.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class musicsqlitehelper(context: Context):SQLiteOpenHelper(context, dbname,null, dbversion) {
    companion object{
        private val dbname = "dbmusic"
        private val tblname = "music"
        private val dbversion = 1
        private val column1 = "Musicid"
        private val column2 = "Songname"
        private val column3 = "Artistname"
        private val column4 = "MusicRating"
        private val column5 = "MusicVerdict"
        private val column6 = "MusicReleaseDate"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        val query = "Create table $tblname($column1 Integer Primary Key AutoIncrement,$column2 Text,$column3 Text,$column4 Text,$column5 Text,$column6 Text)"
        p0?.execSQL(query)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        val query = "Drop table $tblname"
        p0?.execSQL(query)
        onCreate(p0)
    }

    fun AddMusic(musiclist : music):Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(column1, musiclist.Musicid)
        values.put(column2, musiclist.Songname)
        values.put(column3, musiclist.Artistname)
        values.put(column4, musiclist.MusicRating)
        values.put(column5, musiclist.MusicVerdict)
        values.put(column6, musiclist.MusicReleaseDate)

        val result = db.insert(tblname, null, values)
        if (Integer.parseInt(result.toString()) == -1) {
            return false
        } else {
            return true
        }
    }

        fun UpdateMusic(musiclist: music):Boolean
        {
            val db = this.writableDatabase
            var values = ContentValues()
            values.put(column2,musiclist.Songname)
            values.put(column3,musiclist.Artistname)
            values.put(column4,musiclist.MusicRating)
            values.put(column5,musiclist.MusicVerdict)
            values.put(column6,musiclist.MusicReleaseDate)

            val result = db.update(tblname,values,"Musicid = "+ musiclist.Musicid,null)
            if (Integer.parseInt(result.toString())==-1)
            {
                return false
            }
            else{
                return true
            }
        }

        fun DeleteMusic(Musicid : music):Boolean
        {
            val db = this.writableDatabase
            var result = db.delete(tblname,"Musicid = "+ Musicid ,null)
            if (Integer.parseInt(result.toString())==-1)
            {
                return false
            }
            else{
                return true
            }
        }

        fun ShowMusic():Cursor
        {
            val db = this.readableDatabase
            var cursor : Cursor?=null
            cursor = db.rawQuery("Select * from $tblname",null)
            return cursor;
        }
    }