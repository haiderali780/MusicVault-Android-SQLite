package com.example.practice1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practice1.database.register
import com.example.practice1.database.sqlitehelper

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val username : EditText = findViewById(R.id.username)
        val password : EditText = findViewById(R.id.password)
        val emailid : EditText = findViewById(R.id.emailid)
        val phonenumber : EditText = findViewById(R.id.phonenumber)
        val button1 : Button = findViewById(R.id.btn1)

        button1.setOnClickListener(View.OnClickListener {
            val dbhelper : sqlitehelper = sqlitehelper(this)
            val username : String = username.text.toString()
            val password = Integer.parseInt(password.text.toString())
            val emailid : String = emailid.text.toString()
            val phonenumber : String = phonenumber.text.toString()

            val result = dbhelper.addregister(register(username,password,emailid,phonenumber))
            if (result==true)
            {
                Toast.makeText(this,"Register Successfully...",Toast.LENGTH_LONG).show()
            }
            else {
                Toast.makeText(this,"Register Failed...",Toast.LENGTH_LONG).show()
            }

            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        })


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}