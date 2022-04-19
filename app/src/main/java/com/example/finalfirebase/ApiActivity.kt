package com.example.finalfirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.finalfirebase.viewmodel.EpisoDateViewModel

class ApiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_api)

        val viewModel= ViewModelProvider(this).get(EpisoDateViewModel::class.java)
    }
}