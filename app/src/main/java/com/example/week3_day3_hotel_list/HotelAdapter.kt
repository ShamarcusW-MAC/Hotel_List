package com.example.week3_day3_hotel_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class HotelAdapter(val guestList:List<Guest>): BaseAdapter() {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {


        val view = LayoutInflater.from(parent?.context)
            .inflate(R.layout.list_item_layout, parent, false)

        view.findViewById<TextView>(R.id.number_textview).text = guestList[position].roomNumber.toString()
        view.findViewById<TextView>(R.id.name_textview).text = guestList[position].name
        view.findViewById<TextView>(R.id.price_textview).text = guestList[position].price.toString()

        return view
    }

    override fun getItem(position: Int): Any {
        return guestList[position]
    }

    override fun getItemId(position: Int): Long {

        return position.toLong()
    }

    override fun getCount(): Int {
        return guestList.size
    }

}