package com.example.project_andorid_uno

import android.os.Parcel
import android.os.Parcelable

data class ResultData(var playerPoints: String, var botPoints: String) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()?:"",
        parcel.readString()?:"",
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p0: Parcel, p1: Int) {
        p0.writeString(playerPoints)
        p0.writeString(botPoints)
    }

    companion object CREATOR : Parcelable.Creator<ResultData> {
        override fun createFromParcel(parcel: Parcel): ResultData {
            return ResultData(parcel.readString() ?:"",
                parcel.readString()?:"")
        }

        override fun newArray(size: Int): Array<ResultData?> {
            return arrayOfNulls(size)
        }
    }

}
