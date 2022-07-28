
package com.example.kotlinroomdatabaseapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupActionBarWithNavController(findNavController(R.id.fragments))
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController=findNavController(R.id.fragments)
        return navController.navigateUp()||super.onSupportNavigateUp()
    }
}