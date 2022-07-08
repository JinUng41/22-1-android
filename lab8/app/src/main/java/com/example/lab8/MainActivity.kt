package com.example.lab8

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.annotation.Dimension
import androidx.preference.PreferenceManager
import com.example.lab8.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val pref by lazy {
        PreferenceManager.getDefaultSharedPreferences(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.textView.setOnClickListener{
            startActivity(Intent(this, SettingsActivity::class.java ))
        }

    }

    override fun onStart() {
        super.onStart()

        val name = pref.getString("name", "Hello World!")
        binding.textView.setText("${name}")
        val size = pref.getString("size","")

        if(size.equals("small")){
            binding.textView.setTextSize(Dimension.SP,10f)
        }
        if(size.equals("medium")){
            binding.textView.setTextSize(Dimension.SP,14f)
        }
        if(size.equals("big")){
            binding.textView.setTextSize(Dimension.SP,20f)
        }

        if(pref.getBoolean("sync",false)) {
            binding.textView.setTypeface(null, Typeface.NORMAL)
        }
        if(pref.getBoolean("sync",true)) {
            binding.textView.setTypeface(null, Typeface.ITALIC)
        }


    }
}