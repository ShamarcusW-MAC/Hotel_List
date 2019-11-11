package com.example.week3_day3_hotel_list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object{
        lateinit var guest: Guest
    }


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
                val intent = Intent(this, DeleteActivity::class.java)
                 guest = Guest(name_edittext.text.toString(),
                    Integer.parseInt(number_edittext.text.toString()),
                    Integer.parseInt(price_edittext.text.toString()))
                intent.putExtra("Guest", guest)

                startActivity(intent)
                clearFields()



            }
        }

        display_button.setOnClickListener {

            val intent2 = Intent(this, ListActivity::class.java)
            startActivity(intent2)

        }

    }

    private fun clearFields(){
        name_edittext.text.clear()
        number_edittext.text.clear()
        price_edittext.text.clear()
    }

}
