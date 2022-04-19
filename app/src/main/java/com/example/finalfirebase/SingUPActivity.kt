package com.example.finalfirebase

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import com.example.finalfirebase.databinding.ActivityLoginBinding
import com.example.finalfirebase.databinding.ActivitySingUpactivityBinding
import com.google.firebase.auth.FirebaseAuth

class SingUPActivity : AppCompatActivity() {

    // viewBinding
private lateinit var binding:ActivitySingUpactivityBinding

//Action bar
private lateinit var actionBar: ActionBar

// progressDialog
 private lateinit var progressDialog:ProgressDialog

 //FirebaseAuth

   private lateinit var firebaseAuth: FirebaseAuth
   private var email=""
    private var password=""






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingUpactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //configure Actionbar
        actionBar=supportActionBar!!
        actionBar.title="SIGN UP"

        // enable back button
        actionBar.setDisplayShowCustomEnabled(true)
        actionBar.setDisplayShowCustomEnabled(true)

        //configure progress dialog
        //Configure progress dialog
        progressDialog= ProgressDialog(this)
        progressDialog.setTitle("Please wait")
        progressDialog.setMessage("Creating account In...")
        progressDialog.setCanceledOnTouchOutside(false)

        // init firebase auth
        firebaseAuth = FirebaseAuth.getInstance()

        // handle click begin signup
        binding.Singupbtn.setOnClickListener {
            // validate data
            validateData()
        }

    }

    private fun validateData() {
        // get data
        email=binding.emailEt.text.toString().trim()
        password=binding.passwordEt.text.toString().trim()

        //validate data
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){

            // invalid email format
            binding.emailEt.error="INVALID EMAIL FORMAT"
        }
        else if (TextUtils.isEmpty(password)){
            // password isn't entered
            binding.passwordEt.error="PLEASE ENTER PASSWORD"
        }
        else if (password.length <6){
            //password length is less than  6
            binding.passwordEt.error="PASSWORD MUST ATLEAST 6 CHRACTERS LONG"

            }
        else{
            //data is valid, continue signup
            firebaseSignUp()
        }
    }

    private fun firebaseSignUp() {
        //show progress
        progressDialog.show()

        // create account
        firebaseAuth.createUserWithEmailAndPassword(email,password)
            .addOnSuccessListener {
                // signup sucess
                progressDialog.dismiss()
                //
                val firebaseUser =firebaseAuth.currentUser
                val email=firebaseUser!!.email
                Toast.makeText(this,"ACCOUNT CREATED WITH EMAIL $email",Toast.LENGTH_SHORT).show()

                //open profile
                startActivity(Intent(this,ProfileActivity::class.java))
                finish()


            }
            .addOnFailureListener { e->
                // signup failed
            }
        progressDialog.dismiss()
        Toast.makeText(this,"SignUp Failed",Toast.LENGTH_SHORT).show()

            }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()// go back to previous activity,when back button of action bar clicked

        return super.onSupportNavigateUp()
    }

}


