package com.example.finalfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.example.finalfirebase.databinding.ActivityProfileBinding
import com.google.firebase.auth.FirebaseAuth

class ProfileActivity : AppCompatActivity() {

    //viewBinding
    private lateinit var binding:ActivityProfileBinding
    //ActionBar
    private lateinit var actionBar: ActionBar
    //FirebaseAuth
    private  lateinit var  firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        //configure ActionBar
        actionBar=supportActionBar!!
        actionBar.title="PROFILE"

        //init firebase auth
        firebaseAuth= FirebaseAuth.getInstance()
        checkUser()

        //handle click, logout
        binding.logoutBtn.setOnClickListener {
            firebaseAuth.signOut()
            checkUser()
        }

    }

    private fun checkUser() {
        // check user is logged in  or not
        val firebaseUser=firebaseAuth.currentUser
        if (firebaseUser !=null){
            // user not null, user is logged in get user info
            val email=firebaseUser.email
            // set to text view
            binding.emailTV.text= email
        }
        else {
            //user is null, user is not loggeding,goto login activity
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}