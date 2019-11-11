package com.example.week3_day3_hotel_list

import android.os.Parcel
import android.os.Parcelable

data class Guest (val name: String?, val roomNumber: Int, val price: Int) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(roomNumber)
        parcel.writeInt(price)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Guest> {
        override fun createFromParcel(parcel: Parcel): Guest {
            return Guest(parcel)
        }

        override fun newArray(size: Int): Array<Guest?> {
            return arrayOfNulls<Guest>(size)
//            return Array<Guest>(size)
        }
    }
}