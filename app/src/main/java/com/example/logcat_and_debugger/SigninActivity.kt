package com.example.logcat_and_debugger

import android.content.Intent
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
    companion object {  //when we what to make global level variable
        const val KEY = "com.example.logcat_and_debugger.SigninActivity.KEY"
    }

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
                Toast.makeText(this, "Please Enter Your Username", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun readData(uniqueId : String){
        databaseReference = FirebaseDatabase.getInstance().getReference("Users")
        databaseReference.child(uniqueId).get().addOnSuccessListener {
            //if users exists or not
            if(it.exists()){
               //new page new intent
                val email = it.child("email").value
                val name = it.child("name").value
                val id = it.child("username").value
                val intentWelcome = Intent(this, HomeActivity::class.java)
                intentWelcome.putExtra(KEY , email.toString())
                intentWelcome.putExtra(KEY , name.toString())
                intentWelcome.putExtra(KEY , id.toString())
                startActivity(intentWelcome)
            }
            else{
                Toast.makeText(this , "User Doesn't Exists", Toast.LENGTH_SHORT).show()
            }
        }
            .addOnFailureListener {
                Toast.makeText(this , "Failed", Toast.LENGTH_SHORT).show()
            }
    }
}