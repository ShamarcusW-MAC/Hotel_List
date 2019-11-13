package com.example.week3_day3_hotel_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class HotelAdapter(private val guestList:List<Guest>, private val delegate: GuestAdapterDelegate): BaseAdapter() {


    interface GuestAdapterDelegate{
        fun deleteBooking(guestPosition: Int)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view = LayoutInflater.from(parent?.context)
            .inflate(R.layout.list_item_layout, parent, false)

        view.findViewById<TextView>(R.id.guest_info_textView).text = guestList[position].name
//        view.findViewById<TextView>(R.id.guest_room_textView).text = guestList[position].roomNumber.toString()
//        view.findViewById<TextView>(R.id.guest_price_textView).text = "$${guestList[position].price}"


        view.findViewById<ImageView>(R.id.deleteUser_imageView).setOnClickListener{ _ ->

            delegate.deleteBooking(position)
        }
        return view
    }

    override fun getItem(position: Int): Guest {
        return guestList[position]
    }

    override fun getItemId(position: Int): Long {

        return position.toLong()
    }

    override fun getCount(): Int {
        return guestList.size
    }


}