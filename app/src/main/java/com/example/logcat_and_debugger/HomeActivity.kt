package com.example.logcat_and_debugger

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //aba hamileyy yaha bata data linu paryo here KEY play vital role
        val name = intent.getStringExtra(SigninActivity.KEY2)
        val mail = intent.getStringExtra(SigninActivity.KEY1)
        val userid = intent.getStringExtra(SigninActivity.KEY3)

        val welcometxt = findViewById<TextView>(R.id.textwelcome)
        val mailtxt = findViewById<TextView>(R.id.tvMail)
        val idtxt = findViewById<TextView>(R.id.tvUnique)

        welcometxt.text = "Welcome $name"
        mailtxt.text = "Mail: $mail"
        idtxt.text = "UserId: $userid"

    }
}