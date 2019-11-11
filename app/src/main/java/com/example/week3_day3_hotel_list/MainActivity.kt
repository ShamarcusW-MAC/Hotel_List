package com.example.week3_day3_hotel_list

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_delete.*
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.File
import java.io.FileOutputStream
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {

    companion object{

        private var guestList = mutableListOf<Guest>()
        val newAdapter = HotelAdapter(guestList)


    }

    private val fileName = "HotelVip.txt"

    private val filePath = "MyExternalPath"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        submit_button.setOnClickListener { _->
            if(name_edittext.text.toString() == ""  ||
                number_edittext.text.toString() == ""   ||
                price_edittext.text.toString() == "")
            {
                Toast.makeText(this, "Are you blind son?! ", Toast.LENGTH_LONG).show()
            }
            else {
//                writeToFile()
                val intent = Intent(this, DeleteActivity::class.java)
                intent.putExtra("Name", name_edittext.text.toString())
                intent.putExtra("Room", number_edittext.text.toString())
                intent.putExtra("Price", price_edittext.text.toString())


                writeToExternal()

                Log.d("Nameofguest", name_edittext.text.toString())
//                startActivity(intent)

            }
        }

        display_button.setOnClickListener {

            val intent2 = Intent(this, DeleteActivity::class.java)
            startActivityForResult(intent2, 1)

        }

    }


    private fun writeToExternal(){

        val actualFile = File(getExternalFilesDir(filePath), fileName)
        val fOutputStream = FileOutputStream(actualFile, true)

        val inputString = name_edittext.text.toString() +
                " " + number_edittext.text.toString() +
                " " + price_edittext.text.toString()

        clearFields()

        fOutputStream.write(inputString.toByteArray())

        fOutputStream.close()
        Toast.makeText(this, "Room #${number_edittext.text} checked into!", Toast.LENGTH_SHORT).show()


        readFromExternal()
    }

    private fun readFromExternal(){
//        try {
            val actualFile = File(getExternalFilesDir(filePath), fileName)
            val fileInputStream = actualFile.inputStream()
            val inputStreamReader = InputStreamReader(fileInputStream)
            val bufferedReader = BufferedReader(inputStreamReader)
            guestList = mutableListOf()

            var inString: String? = null

//        Log.d("File", inString)

            var a = 0
            var b = 0
            while ({ inString = bufferedReader.readLine(); inString }() != null) {

                guestList.add(Guest(inString.toString(), a, b))
                a++
                b++

            }
            bufferedReader.close()
//
//            usersNames_listview.adapter = newAdapter
//        } catch (throwable: Throwable) {
//            ErrorLogger.logThrowable(throwable)
//        }

    }
    private fun clearFields(){
        name_edittext.text.clear()
        number_edittext.text.clear()
        price_edittext.text.clear()
    }

}
