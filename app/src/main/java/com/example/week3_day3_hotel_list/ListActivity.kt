package com.example.week3_day3_hotel_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_list.*
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader

class ListActivity : AppCompatActivity() {

    private val fileName = "HotelVip.txt"

    private val filePath = "MyExternalPath"

    private var guestList = mutableListOf<Guest>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        if(userdisplay_listview != null) {
            readFromExternal()
        }
    }



    private fun readFromExternal(){
        val actualFile = File(getExternalFilesDir(filePath), fileName)
        val fileInputStream = actualFile.inputStream()
        val inputStreamReader = InputStreamReader(fileInputStream)
        val bufferedReader = BufferedReader(inputStreamReader)
        guestList = mutableListOf()

        var inString: String? = null


        var a = 0
        var b = 0
        while ({ inString = bufferedReader.readLine(); inString }() != null) {

            guestList.add(Guest(inString.toString(), a, b))
            a++
            b++

        }
        bufferedReader.close()

        val newAdapter = HotelAdapter(guestList)
        userdisplay_listview.adapter = newAdapter


    }
}
