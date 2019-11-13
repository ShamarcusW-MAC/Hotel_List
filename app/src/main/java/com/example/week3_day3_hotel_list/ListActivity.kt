package com.example.week3_day3_hotel_list

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.indexOf
import android.view.View
import android.widget.BaseAdapter
import android.widget.Toast
import com.example.week3_day3_hotel_list.DeleteActivity.Companion.guestList
import com.example.week3_day3_hotel_list.MainActivity.Companion.guest
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.list_item_layout.*
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader

class ListActivity : AppCompatActivity(), HotelAdapter.GuestAdapterDelegate {

    private val fileName = "HotelVip.txt"

    private val filePath = "MyExternalPath"


    var name: String? = null
    var room: Int = 0
    var price: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val intent = getIntent()

        if(intent.extras != null){
            guest = intent.getParcelableExtra("Guest")
                name = guest.name
                room = guest.roomNumber
                price = guest.price

                writeToExternal()

            }
        else {


        }

    }

    override fun onResume() {
        super.onResume()
        readFromExternal()
    }

    private fun writeToExternal(){

        val fileOutputStream = openFileOutput(fileName, Context.MODE_APPEND)
        val inputString =
            "\n${name}: #${room}    ${price}"
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

            guestList.add(Guest(inString.toString(), guest.roomNumber, guest.price))


        }
        val newAdapter = HotelAdapter(guestList, this)
        userdisplay_listview.adapter = newAdapter

    }

    override fun deleteBooking(guestPosition: Int) {
        deleteItem(guestPosition)
    }

    private fun deleteItem(position: Int) {
        guestList.removeAt(position)
        var fileOutputStream = openFileOutput(fileName, Context.MODE_PRIVATE)
        for (i in 0 until guestList.size) {
            val guestAsString =
                "${guestList.get(i).name}\n"
            if (i == 0)
                fileOutputStream.write(guestAsString.toByteArray())
            else {
                fileOutputStream = openFileOutput(fileName, Context.MODE_APPEND)
                fileOutputStream.write(guestAsString.toByteArray())
            }
        }
        Toast.makeText(this, "Guest checked out!", Toast.LENGTH_SHORT).show()
        (userdisplay_listview.adapter as BaseAdapter).notifyDataSetChanged()
        readFromExternal()
    }
}
