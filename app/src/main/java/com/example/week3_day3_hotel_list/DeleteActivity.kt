package com.example.week3_day3_hotel_list

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_delete.*
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.File
import java.io.FileOutputStream
import java.io.InputStreamReader

class DeleteActivity : AppCompatActivity(), HotelAdapter.GuestAdapterDelegate {

    private val fileName = "HotelVip.txt"

    private val filePath = "MyExternalPath"

    val newAdapter = HotelAdapter(guestList, this)


    companion object {

       var guestList = mutableListOf<Guest>()

   }


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

                confirm_textview.setText("${name} is now booked in Room #${room}.")
            }

        }

    }

    private fun writeToExternal(){

        val fileOutputStream = openFileOutput(fileName, Context.MODE_APPEND)
        val inputString =
            "\n${name}: #${room}    $${price}"
        fileOutputStream.write(inputString.toByteArray())
        Toast.makeText(
            this,
            "Room #${room} checked into!",
            Toast.LENGTH_SHORT
        ).show()

        readFromExternal()
    }


    private fun readFromExternal(){
        val fileInputStream = openFileInput(fileName)
        val fileInputStreamReader = InputStreamReader(fileInputStream)
        val bufferedReader = BufferedReader(fileInputStreamReader)
        guestList = mutableListOf()

        var inString: String? = null
        val delimiter = ":"


        var a = 0
        var b = 0
        while ({ inString = bufferedReader.readLine(); inString }() != null) {

            guestList.add(Guest(inString.toString(), a, b))


        }

    }
    override fun deleteBooking(guestPosition: Int) {
    }


}
