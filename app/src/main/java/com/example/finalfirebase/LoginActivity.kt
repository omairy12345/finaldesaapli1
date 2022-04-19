package com.example.finalfirebase

import android.app.ActionBar
import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PatternMatcher
import android.telephony.ims.RegistrationManager
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import com.example.finalfirebase.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginActivity : AppCompatActivity() {
    //viewBinding
    private lateinit var binding: ActivityLoginBinding

    //ActionBar
    private lateinit var actionBar: androidx.appcompat.app.ActionBar

    // progress dialog
    private lateinit var progressDialog: ProgressDialog


    //firebaseAuth
    private lateinit var firebaseAuth: FirebaseAuth
    private var email=""
    private var password=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //configure actionbar
        actionBar= supportActionBar!!
        actionBar.title="LOGIN"

        //Configure progress dialog
        progressDialog= ProgressDialog(this)
        progressDialog.setTitle("Please wait")
        progressDialog.setMessage("LOGGIN IN...")
        progressDialog.setCanceledOnTouchOutside(false)


        // init firebaseAuth
        firebaseAuth= FirebaseAuth.getInstance()
        checkUser()

        //handle click open Signup activity

        binding.noAccountTV.setOnClickListener {
            startActivity(Intent(this,SingUPActivity::class.java))


        }


        //handle click, begin login (click inicio de sesion)
        binding.loginbtn.setOnClickListener {
            // before loggin in, validate data

            ValidateData()

        }

    }

    private fun ValidateData() {
        //get data
        email=binding.emailEt.text.toString().trim()
        password=binding.passwordEt.text.toString().trim()

        //validate data
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            // invalid email format
            binding.emailEt.error = "INVALID EMAIL FORMAT"
        }
        else if (TextUtils.isEmpty(password)) {
            //no password entered ( ninguna contraseña ingresada)
            binding.passwordEt.error = "PLEASE ENTER PASSWORD"
        }
            else{
                //data is validated, begin login
                firebaseLogin()


            }
        }

    private fun firebaseLogin() {
        // show progress
        progressDialog.show()
        firebaseAuth.signInWithEmailAndPassword(email,password)
            .addOnSuccessListener {
            //login success
                progressDialog.dismiss()
                // get user info
                val firebaseUser = firebaseAuth.currentUser
                val email=firebaseUser!!.email
                Toast.makeText(this,"LOGGED IN AS $email",Toast.LENGTH_SHORT).show()
                // open profile
                startActivity(Intent(this,ProfileActivity::class.java))
                finish()

            }
            .addOnSuccessListener { e->
                // login failed
                progressDialog.dismiss()
                Toast.makeText(this,"LOGIN FAILED  DUE TO ",Toast.LENGTH_SHORT).show()

            }
}

    private fun checkUser() {
        // if user is alredy logged in go to profile activity (
        //si el usuario ya ha iniciado sesión, vaya a la actividad del perfil)
        //get current user (obtener usuario actual)

        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser!=null) {

            //user is logged in (el usuario ha iniciado sesion)
            startActivity(Intent(this,ProfileActivity::class.java))
            finish()

        }

    }
}