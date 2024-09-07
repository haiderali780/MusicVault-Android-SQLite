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
import com.example.practice1.database.login
import com.example.practice1.database.loginsqlitehelper

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.login)

        val emailid : EditText = findViewById(R.id.emailid)
        val password : EditText = findViewById(R.id.password)
        val button1 : Button = findViewById(R.id.btn1)
        val button2 : Button = findViewById(R.id.btn2)

        button1.setOnClickListener(View.OnClickListener {
            val dbhelper : loginsqlitehelper = loginsqlitehelper(this)
            val emailid : String = emailid.text.toString()
            val password = Integer.parseInt(password.text.toString())

            val result = dbhelper.addlogin(login(emailid,password))
            if (result==true)
            {
                Toast.makeText(this,"Login Successfully...",Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this,"Login Failed...",Toast.LENGTH_LONG).show()
            }

            val intent1 = Intent(this,MusicActivity::class.java)
            startActivity(intent1)
        })

        button2.setOnClickListener(View.OnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        })


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}