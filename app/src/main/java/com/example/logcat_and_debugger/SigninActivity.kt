package com.example.logcat_and_debugger

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SigninActivity : AppCompatActivity() {

    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signin)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val signinButton = findViewById<Button>(R.id.buttonsignin)
        val username = findViewById<TextInputEditText>(R.id.lgusername)

        signinButton.setOnClickListener {
            val uniqueid = username.text.toString()
            //take refernce upto user node
            if(uniqueid.isNotEmpty()){
                readData(uniqueid)
            }
            else{
                Toast.makeText(this, "Please Enter You Usernmae", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun readData(uniqueId : String){
        databaseReference = FirebaseDatabase.getInstance().getReference("Users")
        databaseReference.child(uniqueId).get().addOnSuccessListener {

        }
            .addOnFailureListener {
                Toast.makeText(this , "User Doesnot Exists", Toast.LENGTH_SHORT).show()
            }
    }
}