package com.example.practice1

import android.database.Cursor
import android.os.Bundle
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practice1.database.music
import com.example.practice1.database.musicsqlitehelper

class MusicActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.music)

        val musicid : EditText = findViewById(R.id.musicid)
        val songname : EditText = findViewById(R.id.songname)
        val artistname : EditText = findViewById(R.id.artistname)
        val musicrating : EditText = findViewById(R.id.musicrating)
        val musicverdict : EditText = findViewById(R.id.musicverdict)
        val musicreleasedate : EditText = findViewById(R.id.musicreleasedate)
        val btn1 : Button = findViewById(R.id.btn1)
        val btn2 : Button = findViewById(R.id.btn2)
        val btn3 :Button = findViewById(R.id.btn3)
        val btn4 : Button = findViewById(R.id.btn4)
        val listview1 : ListView = findViewById(R.id.listview1)

        btn1.setOnClickListener(View.OnClickListener {
            val dbhelper :musicsqlitehelper = musicsqlitehelper(this)
            val musicid = Integer.parseInt(musicid.text.toString())
            val songname : String = songname.text.toString()
            val artistname : String = artistname.text.toString()
            val musicrating : String = musicrating.text.toString()
            val musicverdict : String = musicverdict.text.toString()
            val musicreleasedate : String = musicreleasedate.text.toString()

            val result = dbhelper.AddMusic(music(musicid,songname,artistname,musicrating,musicverdict,musicreleasedate))
            if (result==true)
            {
                Toast.makeText(this,"Added Music SuccessFully...",Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this,"Added Music Failed...",Toast.LENGTH_LONG).show()
            }
        })

        btn2.setOnClickListener(View.OnClickListener {
            val dbhelper : musicsqlitehelper = musicsqlitehelper(this)
            val musicid = Integer.parseInt(musicid.text.toString())
            val songname : String = songname.text.toString()
            val artistname : String = artistname.text.toString()
            val musicrating : String = musicrating.text.toString()
            val musicverdict : String = musicverdict.text.toString()
            val musicreleasedate : String = musicreleasedate.text.toString()

            val result = dbhelper.UpdateMusic(music(musicid,songname,artistname,musicrating,musicverdict,musicreleasedate))
            if (result==true)
            {
                Toast.makeText(this,"Updated Music SuccessFully...",Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this,"Updated Music Failed...",Toast.LENGTH_LONG).show()
            }
        })

        btn3.setOnClickListener(View.OnClickListener {
            val dbhelper : musicsqlitehelper = musicsqlitehelper(this)
            val musicid = Integer.parseInt(musicid.text.toString())
            val songname : String = songname.text.toString()
            val artistname : String = artistname.text.toString()
            val musicrating : String = musicrating.text.toString()
            val musicverdict : String = musicverdict.text.toString()
            val musicreleasedate : String = musicreleasedate.text.toString()

            var result = dbhelper.DeleteMusic(music(musicid,songname,artistname,musicrating,musicverdict,musicreleasedate))
            if (result==true)
            {
                Toast.makeText(this,"Deleted Music SuccessFully...",Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this,"Deleted Music Failed...",Toast.LENGTH_LONG).show()
            }
        })

        btn4.setOnClickListener(View.OnClickListener {
            val dbhelper : musicsqlitehelper = musicsqlitehelper(this)
            var musiclist = ArrayList<music>();
            var cursor : Cursor?=null
            cursor = dbhelper.ShowMusic()
            if (cursor.moveToFirst())
            {
                musiclist.add(music(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5)))
                while (cursor.moveToNext())
                {
                    musiclist.add(music(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5)))
                }
            }
            var adpater : Adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,musiclist)
            listview1.adapter = adpater as ListAdapter?
        })

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}