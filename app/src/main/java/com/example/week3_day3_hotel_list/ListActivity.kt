package com.example.week3_day3_hotel_list

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.indexOf
import android.view.View
import android.widget.Toast
import com.example.week3_day3_hotel_list.DeleteActivity.Companion.guestList
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.list_item_layout.*
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader

class ListActivity : AppCompatActivity(), HotelAdapter.GuestAdapterDelegate {

    private val fileName = "HotelVip.txt"

    private val filePath = "MyExternalPath"

//    private var guestList = mutableListOf<Guest>()
//    val newAdapter = HotelAdapter(guestList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)


        if(userdisplay_listview != null) {
            readFromExternal()
        }

//       deleteUser_imageView.setOnClickListener(View.OnClickListener {


//           DeleteActivity.guestList.removeAt(0)
//           newAdapter.notifyDataSetChanged()
//       })


//
    }

    override fun onResume() {
        super.onResume()
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
        val newAdapter = HotelAdapter(guestList, this)
        userdisplay_listview.adapter = newAdapter
        newAdapter.notifyDataSetChanged()

    }

    override fun deleteBooking(guestPosition: Int) {
        deleteItem(guestPosition)
    }

    private fun deleteItem(position: Int) {
        guestList.removeAt(position)
        var fileOutputStream = openFileOutput(fileName, Context.MODE_PRIVATE)
        for (i in 0 until guestList.size) {
            val guestAsString =
                "${guestList.get(i).name}: #${guestList.get(i).roomNumber} $${guestList.get(i).price}:"
            if (i == 0)
                fileOutputStream.write(guestAsString.toByteArray())
            else {
                fileOutputStream = openFileOutput(fileName, Context.MODE_APPEND)
                fileOutputStream.write(guestAsString.toByteArray())
            }
        }
        Toast.makeText(this, "Guest checked out!", Toast.LENGTH_SHORT).show()
        readFromExternal()
    }
}
