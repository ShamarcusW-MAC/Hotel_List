package com.example.week3_day3_hotel_list

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_delete.*
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.File
import java.io.FileOutputStream
import java.io.InputStreamReader

class DeleteActivity : AppCompatActivity() {



    private val fileName = "HotelVip.txt"

    private val filePath = "MyExternalPath"

    private var guestList = mutableListOf<Guest>()

    var name: String? = null
    var room: Int = 0
    var price: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete)
        val intent = getIntent()

        if(intent.getParcelableExtra<Guest>("Guest").equals(MainActivity.guest)){
            var guest: Guest = intent.getParcelableExtra("Guest")
            if(guest == null )
            {

            }else {
                name = guest.name
                room = guest.roomNumber
                price = guest.price

                writeToExternal()
            }

        }




    }



    private fun writeToExternal(){

        val actualFile = File(getExternalFilesDir(filePath), fileName)
        val fOutputStream = FileOutputStream(actualFile, true)

        val inputString = name +
                ":  #" + room+
                " $" + price + "\n"


        fOutputStream.write(inputString.toByteArray())

        fOutputStream.close()
        Toast.makeText(this, "Room #${room} checked into!", Toast.LENGTH_SHORT).show()


        readFromExternal()
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
            usersNames_listview.adapter = newAdapter


    }

}
