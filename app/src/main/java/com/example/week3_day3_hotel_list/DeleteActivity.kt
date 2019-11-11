package com.example.week3_day3_hotel_list

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_delete.*
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.InputStreamReader

class DeleteActivity : AppCompatActivity() {

    companion object {


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete)
//        var listView: ListView = findViewById(R.id.usersNames_listview)
        val intent = getIntent()
        intent.getStringExtra("Name")
        intent.getStringExtra("Room")
        intent.getStringExtra("Price")
        usersNames_listview.adapter = MainActivity.newAdapter

    }
}
